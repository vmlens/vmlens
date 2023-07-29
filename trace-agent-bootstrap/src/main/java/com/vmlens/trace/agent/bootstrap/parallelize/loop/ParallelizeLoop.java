package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;

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

    public void beginThreadMethodEnter(TestThreadState testThreadState, RunnableOrThreadWrapper beganTask) {
        lock.lock();
        try {
            currentRun.newTask(beganTask, testThreadState);
        } finally {
            lock.unlock();
        }
    }

    public boolean hasNext(TestThreadState testThreadState) {
        lock.lock();
        try {
            if (currentRun != null) {
                currentRun.end(testThreadState);
                if (interleaveLoopIterator.hasNext()) {
                    currentRun = new Run(lock, maxRunId, waitNotifyStrategy, runStateMachineFactory.createRunning(interleaveLoopIterator.next(), testThreadState, interleaveLoop),
                            testThreadState);
                    maxRunId++;
                    return true;
                }
                return false;
            } else {
                currentRun = new Run(lock, maxRunId, waitNotifyStrategy, runStateMachineFactory.createInitial(testThreadState, interleaveLoop),
                        testThreadState);
                maxRunId++;
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    public void close(TestThreadState testThreadState) {
        lock.lock();
        try {
            testThreadState.setParallelizedThreadLocalToNull();
        } finally {
            lock.unlock();
        }
    }

    // For Test
    public Run currentRun() {
        return currentRun;
    }
}
