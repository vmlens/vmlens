package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class HasNextResult {

    private final boolean hasNext;
    private final TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEventList;

    public HasNextResult(boolean hasNext, TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEventList) {
        this.hasNext = hasNext;
        this.serializableEventList = serializableEventList;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEventList() {
        return serializableEventList;
    }
}
