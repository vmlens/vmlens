package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.SendEventContext;

public interface InterleaveActionWithPositionFactoryAndOrRuntimeEvent {
    void apply(ActualRun actualRun, ActualRunContext actualRunContext);
}
