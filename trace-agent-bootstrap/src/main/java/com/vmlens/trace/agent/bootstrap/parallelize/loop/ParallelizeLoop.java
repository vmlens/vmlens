package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;

import java.util.Iterator;

public class ParallelizeLoop {

    private final Object singleLoopAndRunLock = new Object();
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
        synchronized (singleLoopAndRunLock) {
            currentRun.newTask(beganTask, testThreadState);
        }
    }

    public boolean hasNext(TestThreadState testThreadState) {
        synchronized (singleLoopAndRunLock) {
            if (currentRun != null) {
                currentRun.end(testThreadState);
                if (interleaveLoopIterator.hasNext()) {
                    currentRun = new Run(singleLoopAndRunLock, maxRunId, waitNotifyStrategy, runStateMachineFactory.createRunning(interleaveLoopIterator.next(), testThreadState, interleaveLoop),
                            testThreadState);
                    maxRunId++;
                    return true;
                }
                return false;
            } else {
                currentRun = new Run(singleLoopAndRunLock, maxRunId, waitNotifyStrategy, runStateMachineFactory.createInitial(testThreadState, interleaveLoop),
                        testThreadState);
                maxRunId++;
                return true;
            }
        }
    }

    public void close(TestThreadState testThreadState) {
        synchronized (singleLoopAndRunLock) {
            testThreadState.setParallelizedThreadLocalToNull();
        }
    }

    // For Test
    public Run currentRun() {
        return currentRun;
    }
}
