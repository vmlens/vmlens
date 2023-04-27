package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class RunStateMachineFactoryImpl implements RunStateMachineFactory {
    @Override
    public RunStateMachine create(InterleaveRun calculatedRun, TestThreadState testThreadState) {
        return new RunStateMachineImpl(calculatedRun, testThreadState);
    }
}
