package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.anarsoft.trace.agent.description.TestLoopDescription;
import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RunEndEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RunStartEvent;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunObserverForCalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunObserverNoOp;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadLocalDataWhenInTestMap;

import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

// Fixme synchonization herausziehen
public class ParallelizeLoop {

    private final ReentrantLock lock = new ReentrantLock();
    private final int loopId;
    private final RunStateMachineFactory runStateMachineFactory;
    private final Iterator<CalculatedRun> interleaveLoopIterator;
    private final WaitNotifyStrategy waitNotifyStrategy;
    private final InterleaveLoop interleaveLoop;
    private Run currentRun;
    private int maxRunId;

    public ParallelizeLoop(int loopId, RunStateMachineFactory runStateMachineFactory,
                           WaitNotifyStrategy waitNotifyStrategy, InterleaveLoop interleaveLoop) {
        this.loopId = loopId;
        this.runStateMachineFactory = runStateMachineFactory;
        this.interleaveLoopIterator = interleaveLoop.iterator();
        this.waitNotifyStrategy = waitNotifyStrategy;
        this.interleaveLoop = interleaveLoop;
    }

    public void beginThreadMethodEnter(ThreadLocalForParallelize threadLocalForParallelize, RunnableOrThreadWrapper beganTask) {
        lock.lock();
        try {
            currentRun.newTask(beganTask, threadLocalForParallelize);
        } finally {
            lock.unlock();
        }
    }

    public HasNextResult hasNext(ThreadLocalForParallelize threadLocalForParallelize, Object obj) {
        lock.lock();
        try {
            if (currentRun != null) {
                RunEndEvent endEvent = new RunEndEvent(loopId, currentRun.runId());

                ActualRun previous = currentRun.end(threadLocalForParallelize);
                interleaveLoop.addActualRun(previous);

                if (interleaveLoopIterator.hasNext()) {
                    CalculatedRun calculatedReun = interleaveLoopIterator.next();
                    ActualRun actualRun = new ActualRun(new ActualRunObserverForCalculatedRun(calculatedReun));
                    ThreadLocalDataWhenInTestMap runContext = new ThreadLocalDataWhenInTestMap();
                    currentRun = new RunImpl(lock, waitNotifyStrategy, runStateMachineFactory.createRunning(
                            runContext, calculatedReun, actualRun), loopId, maxRunId);
                    threadLocalForParallelize.setThreadLocalDataWhenInTest(
                            runContext.createForMainTestThread(currentRun, threadLocalForParallelize.threadId()));
                    int tempRunId = maxRunId;
                    maxRunId++;
                    return new HasNextResult(true, new SerializableEvent[]{endEvent, new RunStartEvent(loopId, tempRunId)});
                }
                return new HasNextResult(false, new SerializableEvent[]{endEvent});
            } else {
                ActualRun actualRun = new ActualRun(new ActualRunObserverNoOp());
                ThreadLocalDataWhenInTestMap runContext = new ThreadLocalDataWhenInTestMap();
                currentRun = new RunImpl(lock, waitNotifyStrategy, runStateMachineFactory.createInitial(
                        runContext, actualRun), loopId, maxRunId);
                threadLocalForParallelize.setThreadLocalDataWhenInTest(
                        runContext.createForMainTestThread(currentRun, threadLocalForParallelize.threadId()));
                int tempRunId = maxRunId;
                maxRunId++;
                return new HasNextResult(true, new SerializableEvent[]{new RunStartEvent(loopId, tempRunId),
                        new TestLoopDescription(loopId, ((AllInterleavings) obj).name)});
            }
        } finally {
            lock.unlock();
        }
    }

    public void close(ThreadLocalForParallelize threadLocalForParallelize) {
        lock.lock();
        try {
            threadLocalForParallelize.setParallelizedThreadLocalToNull();
        } finally {
            lock.unlock();
        }
    }

    public int loopId() {
        return loopId;
    }
}
