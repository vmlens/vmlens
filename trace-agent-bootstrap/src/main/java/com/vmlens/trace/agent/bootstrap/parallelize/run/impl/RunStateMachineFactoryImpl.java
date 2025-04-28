package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunWithCalculated;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunWithoutCalculated;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.RunStateActive;

public class RunStateMachineFactoryImpl implements RunStateMachineFactory {

    @Override
    public RunStateMachine createRunning(ThreadIndexAndThreadStateMap runContext, CalculatedRun calculatedRun) {
        return new RunStateMachineImpl(new RunStateContext(runContext,
                new InterleaveRunWithCalculated(calculatedRun.calculatedRunElementArray())));
    }

    @Override
    public RunStateMachine createInitial(ThreadIndexAndThreadStateMap runContext) {
        return new RunStateMachineImpl(new RunStateContext(runContext,new InterleaveRunWithoutCalculated(new ActualRun())));
    }

}
