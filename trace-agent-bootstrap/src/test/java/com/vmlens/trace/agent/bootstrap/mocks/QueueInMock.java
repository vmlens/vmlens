package com.vmlens.trace.agent.bootstrap.mocks;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;

import java.util.List;

public class QueueInMock implements QueueIn {

    private final List<SerializableEvent> eventList;

    public QueueInMock(List<SerializableEvent> eventList) {
        this.eventList = eventList;
    }

    @Override
    public void offer(SerializableEvent element) {
        eventList.add(element);
    }

}
