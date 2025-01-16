package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class HasNextResult {

    private final boolean hasNext;
    private final TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents;

    public HasNextResult(boolean hasNext, TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents) {
        this.hasNext = hasNext;
        this.serializableEvents = serializableEvents;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents() {
        return serializableEvents;
    }
}
