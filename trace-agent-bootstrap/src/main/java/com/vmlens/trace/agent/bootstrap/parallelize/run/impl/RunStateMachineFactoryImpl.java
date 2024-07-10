package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;

public class RunStateMachineFactoryImpl implements RunStateMachineFactory {

    @Override
    public RunStateMachine createRunning(ThreadLocalDataWhenInTestMap runContext, CalculatedRun calculatedRun,
                                         ActualRun actualRun) {
        return new RunStateMachineImpl(actualRun, runContext,
                RunStateActive.createInterleaved(actualRun, runContext, calculatedRun));
    }

    @Override
    public RunStateMachine createInitial(ThreadLocalDataWhenInTestMap runContext,
                                         ActualRun actualRun) {
        return new RunStateMachineImpl(actualRun, runContext,
                RunStateActive.createRecording(actualRun, runContext));
    }

}
