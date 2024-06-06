package com.vmlens.trace.agent.bootstrap.testfixture;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.StaticEvent;

import java.util.List;

public class QueueInMock implements QueueIn {

    private final List<StaticEvent> eventList;

    public QueueInMock(List<StaticEvent> eventList) {
        this.eventList = eventList;
    }

    @Override
    public void offer(Object element) {
        eventList.add((StaticEvent) element);
    }

}
