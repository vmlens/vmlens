package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachineFactory;

public class RunStateMachineFactoryImpl implements RunStateMachineFactory {

    @Override
    public RunStateMachine createRunning(InterleaveLoopContext interleaveLoopContext,
                                         ThreadIndexAndThreadStateMap runContext,
                                         CalculatedRun calculatedRun) {
        return new RunStateMachineImpl(new RunStateContext(runContext,
                new InterleaveRun(interleaveLoopContext,calculatedRun.calculatedRunElementArray())));
    }

    @Override
    public RunStateMachine createInitial(InterleaveLoopContext interleaveLoopContext,ThreadIndexAndThreadStateMap runContext) {
        return new RunStateMachineImpl(new RunStateContext(runContext,new InterleaveRun(interleaveLoopContext)));
    }

}
