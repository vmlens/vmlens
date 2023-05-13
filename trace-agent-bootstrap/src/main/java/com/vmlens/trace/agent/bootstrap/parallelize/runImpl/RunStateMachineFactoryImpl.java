package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunObserverForCalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunObserverNoOp;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class RunStateMachineFactoryImpl implements RunStateMachineFactory {
    @Override
    public RunStateMachine createRunning(CalculatedRun calculatedRun, TestThreadState testThreadState, InterleaveLoop interleaveLoop) {
        ThreadIdToState threadIdToState = new ThreadIdToState();
        ActualRun actualRun = new ActualRun(new ActualRunObserverForCalculatedRun(calculatedRun));
        return new RunStateMachineImpl(actualRun, testThreadState, interleaveLoop, threadIdToState,
                new RunStateRunning(actualRun, calculatedRun, threadIdToState));
    }

    @Override
    public RunStateMachine createInitial(TestThreadState testThreadState, InterleaveLoop interleaveLoop) {
        ThreadIdToState threadIdToState = new ThreadIdToState();
        ActualRun actualRun = new ActualRun(new ActualRunObserverNoOp());
        return new RunStateMachineImpl(actualRun, testThreadState, interleaveLoop, threadIdToState,
                new RunStateRecording(actualRun, threadIdToState));
    }
}
