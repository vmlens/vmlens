package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates.RunStateActive;

public class RunStateMachineFactoryImpl implements RunStateMachineFactory {

    @Override
    public RunStateMachine createRunning(ThreadIndexAndThreadStateMap runContext, CalculatedRun calculatedRun,
                                         ActualRun actualRun) {
        return new RunStateMachineImpl(actualRun, runContext,
                RunStateActive.createInterleaved(calculatedRun));
    }

    @Override
    public RunStateMachine createInitial(ThreadIndexAndThreadStateMap runContext,
                                         ActualRun actualRun) {
        return new RunStateMachineImpl(actualRun, runContext,
                RunStateActive.createRecording());
    }

}
