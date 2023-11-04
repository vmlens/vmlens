package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelizeMock;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.RunContext;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.SendEventContextImpl;
import com.vmlens.trace.agent.bootstrap.testFixture.StaticEventAndId;

import java.util.List;

public class ActualRunContextMock implements ActualRunContext {
    private final CallbackStatePerThreadForParallelizeMock callbackStatePerThreadForParallelizeMock =
            new CallbackStatePerThreadForParallelizeMock();

    @Override
    public SendEventContext sendEventContext() {
        return new SendEventContextImpl(new RunContext(5, 7), callbackStatePerThreadForParallelizeMock);
    }

    public List<StaticEventAndId> actualEvents() {
        return callbackStatePerThreadForParallelizeMock.actualEvents();
    }
}
