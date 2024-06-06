package com.vmlens.trace.agent.bootstrap.parallelize.loop;

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

    // Fixme erzeugen des in test thread local und test
    public boolean hasNext(ThreadLocalForParallelize threadLocalForParallelize) {
        lock.lock();
        try {
            if (currentRun != null) {
                ActualRun previous = currentRun.end(threadLocalForParallelize);
                interleaveLoop.addActualRun(previous);

                if (interleaveLoopIterator.hasNext()) {
                    CalculatedRun calculatedReun = interleaveLoopIterator.next();
                    ActualRun actualRun = new ActualRun(new ActualRunObserverForCalculatedRun(calculatedReun));
                    ThreadLocalDataWhenInTestMap runContext = new ThreadLocalDataWhenInTestMap(loopId, maxRunId);
                    currentRun = new RunImpl(lock, waitNotifyStrategy, runStateMachineFactory.createRunning(
                            runContext, calculatedReun, actualRun));
                    threadLocalForParallelize.setThreadLocalDataWhenInTest(
                            runContext.create(currentRun, threadLocalForParallelize.queueIn(), threadLocalForParallelize.threadId()));
                    maxRunId++;
                    return true;
                }
                return false;
            } else {
                ActualRun actualRun = new ActualRun(new ActualRunObserverNoOp());
                ThreadLocalDataWhenInTestMap runContext = new ThreadLocalDataWhenInTestMap(loopId, maxRunId);
                currentRun = new RunImpl(lock, waitNotifyStrategy, runStateMachineFactory.createInitial(
                        runContext, actualRun));
                threadLocalForParallelize.setThreadLocalDataWhenInTest(
                        runContext.create(currentRun, threadLocalForParallelize.queueIn(), threadLocalForParallelize.threadId()));
                maxRunId++;
                return true;
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

    // For Test
    public Run currentRun() {
        return currentRun;
    }
}
