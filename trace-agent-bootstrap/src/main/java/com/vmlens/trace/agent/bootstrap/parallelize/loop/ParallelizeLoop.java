package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

import java.util.Iterator;

public class ParallelizeLoop {

    private final int loopId;
    private final RunStateMachineFactory runStateMachineFactory;
    private final Iterator<InterleaveRun> interleaveLoopIterator;
    private final WaitNotifyStrategy waitNotifyStrategy;
    private Run currentRun;
    private int maxRunId;

    public ParallelizeLoop(int loopId, RunStateMachineFactory runStateMachineFactory, Iterator<InterleaveRun> interleaveLoopIterator,
                           WaitNotifyStrategy waitNotifyStrategy ) {
        this.loopId = loopId;
        this.runStateMachineFactory = runStateMachineFactory;
        this.interleaveLoopIterator = interleaveLoopIterator;
        this.waitNotifyStrategy = waitNotifyStrategy;
     }

    public void beginThreadMethodEnter(TestThreadState testThreadState, RunnableOrThreadWrapper beganTask) {
        currentRun.newTask(beganTask,testThreadState);
    }

    public boolean hasNext(TestThreadState testThreadState) {
       if (currentRun != null) {
            currentRun.end(testThreadState);
        }
        if (interleaveLoopIterator.hasNext()) {
            currentRun = new Run(maxRunId, waitNotifyStrategy, runStateMachineFactory.create(interleaveLoopIterator.next(),testThreadState),
                    testThreadState );
            maxRunId++;
            return true;
        }

        return false;
    }

    public void close(TestThreadState testThreadState) {
        testThreadState.setParallelizedThreadLocalToNull();
    }
}
