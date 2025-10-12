package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.FirstMethodInThread;
import com.vmlens.trace.agent.bootstrap.event.queue.EventQueue;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.serializableeventimpl.RunEndEvent;
import com.vmlens.trace.agent.bootstrap.event.serializableeventimpl.RunStartEvent;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

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
                        ThreadWrapper beganTask,
                        FirstMethodInThread firstMethodInThread) {
        lock.lock();
        try {
              currentRun.newTask(new NewTaskContext(queueIn, beganTask, threadLocalForParallelize,firstMethodInThread));
        } finally {
            lock.unlock();
        }
    }

    public boolean hasNext(ThreadLocalForParallelize threadLocalForParallelize,
                           QueueIn queueIn) {
        lock.lock();
        try {
            if (currentRun != null) {
                // Happen s when hasNext called multiple times after false was returned
                if(currentRun.isEnded()) {
                    return false;
                }
                currentRun.checkAllThreadsJoined();
                RunEndEvent endEvent = new RunEndEvent(loopId, currentRun.runId());
                queueIn.offer(endEvent);
                if(currentRun.runId() == 0) {
                    createInitialRun(threadLocalForParallelize, queueIn);
                    // we skip the first run to avoid
                    // that the initializing logic which is only called once
                    // leads to blocked runs
                    return true;
                }
                ActualRun previous = currentRun.end(threadLocalForParallelize);
                interleaveLoop.addActualRun(previous,queueIn);
                if (interleaveLoopIterator.hasNext()) {
                    CalculatedRun calculatedRun = interleaveLoopIterator.next();
                    ThreadIndexAndThreadStateMap runContext = new ThreadIndexAndThreadStateMap();
                    currentRun = new RunImpl(lock, waitNotifyStrategy,
                            runStateMachineFactory.createRunning(interleaveLoop.interleaveLoopContext(), runContext, calculatedRun),
                            loopId, maxRunId);
                    threadLocalForParallelize.setThreadLocalDataWhenInTest(
                            runContext.createForMainTestThread(currentRun, threadLocalForParallelize, queueIn));
                    int tempRunId = maxRunId;
                    maxRunId++;
                    queueIn.offer(new RunStartEvent(loopId, tempRunId));
                    return true;
                }
                return false;
            } else {
                createInitialRun( threadLocalForParallelize, queueIn);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    private void createInitialRun(ThreadLocalForParallelize threadLocalForParallelize,
                                  QueueIn queueIn) {
        ThreadIndexAndThreadStateMap runContext = new ThreadIndexAndThreadStateMap();
        currentRun = new RunImpl(lock, waitNotifyStrategy,
                runStateMachineFactory.createInitial(interleaveLoop.interleaveLoopContext(), runContext),
                loopId, maxRunId);
        threadLocalForParallelize.setThreadLocalDataWhenInTest(
                runContext.createForMainTestThread(currentRun, threadLocalForParallelize, queueIn));
        int tempRunId = maxRunId;
        maxRunId++;
        queueIn.offer(new RunStartEvent(loopId, tempRunId));
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

    public void checkBlocked(EventQueue eventQueue) {
        lock.lock();
        try {
            if(currentRun != null) {
                currentRun.checkBlocked(eventQueue);
            }
        } finally {
            lock.unlock();
        }
    }

}
