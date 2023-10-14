package com.vmlens.trace.agent.bootstrap.parallelize.actionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.InterleaveActionWithPositionFactoryImpl;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.ContainerForInterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class ParallelizeActionWithInterleaveAction extends ParallelizeActionWithInterleaveActionAndOrRuntimeEvent {

    private final InterleaveAction interleaveAction;


    public ParallelizeActionWithInterleaveAction(InterleaveAction interleaveAction) {
        this.interleaveAction = interleaveAction;
    }

    @Override
    public void addInterleaveActionAndOrEvent(ActionContext context, TestThreadState testThreadState) {
        context.afterInterleaveActionWithPositionFactory(
                new ContainerForInterleaveActionWithPositionFactory(
                        new InterleaveActionWithPositionFactoryImpl(
                                interleaveAction, testThreadState.threadIndex())), testThreadState);
    }
}
