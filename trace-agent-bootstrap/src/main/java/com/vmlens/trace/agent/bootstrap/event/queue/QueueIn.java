package com.vmlens.trace.agent.bootstrap.event.queue;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public abstract class QueueIn {

    public void offer(TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents) {
        for (TLinkableWrapper<SerializableEvent> event : serializableEvents) {
            offer(event.element());
        }
    }

    public abstract void offer(SerializableEvent element);

}
