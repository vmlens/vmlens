package com.vmlens.trace.agent.bootstrap.parallelize.actionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.InterleaveActionWithPositionFactoryImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class InterleaveActionWithPositionFactoryFactory implements ParallelizeAction {

    private final InterleaveAction interleaveAction;


    public InterleaveActionWithPositionFactoryFactory(InterleaveAction interleaveAction) {
        this.interleaveAction = interleaveAction;
    }

    @Override
    public RunState nextState(ActionContext context, TestThreadState testThreadState) {
        return context.current();
    }

    @Override
    public void addInterleaveAction(ActionContext context, TestThreadState testThreadState) {
        context.getCalculatedRun().after(new InterleaveActionWithPositionFactoryImpl(
                interleaveAction, testThreadState.threadIndex()));
    }


}
