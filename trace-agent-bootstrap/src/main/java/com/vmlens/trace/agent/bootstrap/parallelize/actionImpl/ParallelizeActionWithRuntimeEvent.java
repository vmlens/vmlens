package com.vmlens.trace.agent.bootstrap.parallelize.actionImpl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ContainerForRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class ParallelizeActionWithRuntimeEvent extends ParallelizeActionWithInterleaveActionAndOrRuntimeEvent {

    private final RuntimeEvent event;

    public ParallelizeActionWithRuntimeEvent(RuntimeEvent event) {
        this.event = event;
    }

    @Override
    public void addInterleaveActionAndOrEvent(ActionContext context, TestThreadState testThreadState) {
        context.afterInterleaveActionWithPositionFactory(new ContainerForRuntimeEvent(event), testThreadState);
    }
}
