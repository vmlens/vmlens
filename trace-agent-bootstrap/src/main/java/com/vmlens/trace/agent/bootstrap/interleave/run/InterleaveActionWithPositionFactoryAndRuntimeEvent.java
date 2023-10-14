package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;

public class InterleaveActionWithPositionFactoryAndRuntimeEvent implements InterleaveActionWithPositionFactoryAndOrRuntimeEvent {
    private final InterleaveActionWithPositionFactory interleaveActionWithPositionFactory;
    private final RuntimeEvent runtimeEvent;

    public InterleaveActionWithPositionFactoryAndRuntimeEvent(InterleaveActionWithPositionFactory interleaveActionWithPositionFactory,
                                                              RuntimeEvent runtimeEvent) {
        this.interleaveActionWithPositionFactory = interleaveActionWithPositionFactory;
        this.runtimeEvent = runtimeEvent;
    }

    @Override
    public void apply(ActualRun actualRun, ActualRunContext actualRunContext) {

    }
}
