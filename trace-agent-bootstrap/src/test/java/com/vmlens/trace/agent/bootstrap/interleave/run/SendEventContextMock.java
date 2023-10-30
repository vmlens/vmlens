package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.callback.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.StaticEvent;
import com.vmlens.trace.agent.bootstrap.testFixture.StaticEventAndId;

import java.util.LinkedList;
import java.util.List;

public class SendEventContextMock extends PerThreadCounter {

    private List<StaticEventAndId> actualEvents = new
            LinkedList<>();

    @Override
    public long threadId() {
        return 0;
    }

    @Override
    public void put(int id, StaticEvent element, int slidingWindowId) {
        actualEvents.add(new StaticEventAndId(element, id));
    }

    public List<StaticEventAndId> actualEvents() {
        return actualEvents;
    }
}
