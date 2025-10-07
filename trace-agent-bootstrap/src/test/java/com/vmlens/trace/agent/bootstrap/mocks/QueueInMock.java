package com.vmlens.trace.agent.bootstrap.mocks;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

import java.util.LinkedList;
import java.util.List;

public class QueueInMock extends QueueIn {

    private final List<SerializableEvent> eventList;

    public QueueInMock() {
        this(new LinkedList<>());
    }


    public QueueInMock(List<SerializableEvent> eventList) {
        this.eventList = eventList;
    }

    @Override
    public void offer(SerializableEvent element) {
        eventList.add(element);
    }

}
