package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.interleave.run.*;

import java.util.List;

public class ActualRunMock extends ActualRun {

    private final List<InterleaveActionWithPositionFactoryAndOrRuntimeEvent> actualRun;

    public ActualRunMock(List<InterleaveActionWithPositionFactoryAndOrRuntimeEvent> actualRun) {
        super(new ActualRunObserverNoOp());
        this.actualRun = actualRun;
    }

    @Override
    public void after(InterleaveActionWithPositionFactoryAndOrRuntimeEvent interleaveActionWithPositionFactoryAndOrRuntimeEvent,
                      ActualRunContext actualRunContext) {
        actualRun.add(
                interleaveActionWithPositionFactoryAndOrRuntimeEvent);
    }
}
