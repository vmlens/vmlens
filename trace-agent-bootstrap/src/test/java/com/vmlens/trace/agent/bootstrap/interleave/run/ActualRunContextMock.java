package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.testFixture.StaticEventAndId;

import java.util.List;

public class ActualRunContextMock implements ActualRunContext {

    private final SendEventContextMock sendEventContextMock = new SendEventContextMock();

    @Override
    public SendEventContext sendEventContext() {
        return sendEventContextMock;
    }

    @Override
    public void setRunIdsInRuntimeEvent(RuntimeEvent event) {
        event.setRunLoopAndSlidingWindowId(1, 1, 1);
    }

    public List<StaticEventAndId> actualEvents() {
        return sendEventContextMock.actualEvents();
    }
}
