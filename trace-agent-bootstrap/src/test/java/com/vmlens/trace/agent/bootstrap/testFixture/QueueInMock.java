package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.StaticEvent;

import java.util.List;

public class QueueInMock implements QueueIn {

    private final List<StaticEventAndId> eventList;

    public QueueInMock(List<StaticEventAndId> eventList) {
        this.eventList = eventList;
    }

    @Override
    public void put(int id, StaticEvent element, int slidingWindowId) {
        eventList.add(new StaticEventAndId(element, id));
    }

    @Override
    public void putDirect(StaticEvent in) {

    }
}
