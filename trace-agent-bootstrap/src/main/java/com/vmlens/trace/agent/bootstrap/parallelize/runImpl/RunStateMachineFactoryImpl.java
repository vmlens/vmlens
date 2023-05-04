package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class RunStateMachineFactoryImpl implements RunStateMachineFactory {
    @Override
    public RunStateMachine create(ActualRun calculatedRun, TestThreadState testThreadState) {
        return new RunStateMachineImpl(calculatedRun, testThreadState);
    }
}
