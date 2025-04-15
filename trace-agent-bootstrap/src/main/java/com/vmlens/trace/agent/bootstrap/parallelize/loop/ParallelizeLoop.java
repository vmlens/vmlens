package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.serializableeventimpl.RunEndEvent;
import com.vmlens.trace.agent.bootstrap.event.serializableeventimpl.RunStartEvent;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

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

    public void newTask(QueueIn queueIn,
                        ThreadLocalForParallelize threadLocalForParallelize,
                        RunnableOrThreadWrapper beganTask) {
        lock.lock();
        try {
              currentRun.newTask(new NewTaskContext(queueIn, beganTask, threadLocalForParallelize));
        } finally {
            lock.unlock();
        }
    }

    public boolean hasNext(ThreadLocalForParallelize threadLocalForParallelize,
                           TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents) {
        lock.lock();
        try {
            if (currentRun != null) {
                RunEndEvent endEvent = new RunEndEvent(loopId, currentRun.runId());
                serializableEvents.add(wrap(endEvent));

                ActualRun previous = currentRun.end(threadLocalForParallelize);
                interleaveLoop.addActualRun(previous);

                if (interleaveLoopIterator.hasNext()) {
                    CalculatedRun calculatedRun = interleaveLoopIterator.next();
                    ThreadIndexAndThreadStateMap runContext = new ThreadIndexAndThreadStateMap();
                    currentRun = new RunImpl(lock, waitNotifyStrategy, runStateMachineFactory.createRunning(
                            runContext, calculatedRun), loopId, maxRunId);
                    threadLocalForParallelize.setThreadLocalDataWhenInTest(
                            runContext.createForMainTestThread(currentRun, threadLocalForParallelize, serializableEvents));
                    int tempRunId = maxRunId;
                    maxRunId++;

                    serializableEvents.add(wrap(new RunStartEvent(loopId, tempRunId)));
                    return true;
                }
                return false;
            } else {
                ThreadIndexAndThreadStateMap runContext = new ThreadIndexAndThreadStateMap();
                currentRun = new RunImpl(lock, waitNotifyStrategy, runStateMachineFactory.createInitial(
                        runContext), loopId, maxRunId);
                threadLocalForParallelize.setThreadLocalDataWhenInTest(
                        runContext.createForMainTestThread(currentRun, threadLocalForParallelize, serializableEvents));
                int tempRunId = maxRunId;
                maxRunId++;
                serializableEvents.add(wrap(new RunStartEvent(loopId, tempRunId)));
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

    public int loopId() {
        return loopId;
    }
}
