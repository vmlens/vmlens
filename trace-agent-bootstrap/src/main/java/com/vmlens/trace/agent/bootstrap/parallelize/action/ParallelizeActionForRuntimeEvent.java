package com.vmlens.trace.agent.bootstrap.parallelize.action;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;


public class ParallelizeActionForRuntimeEvent extends ParallelizeActionWithoutStateChange {
    private final RuntimeEvent runtimeEvent;

    public ParallelizeActionForRuntimeEvent(RuntimeEvent runtimeEvent) {
        this.runtimeEvent = runtimeEvent;
    }

    @Override
    public void addInterleaveActionAndOrEvent(ActionContext context, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        context.afterInterleaveActionWithPositionFactory(
                runtimeEvent, threadLocalDataWhenInTest);
    }
}
