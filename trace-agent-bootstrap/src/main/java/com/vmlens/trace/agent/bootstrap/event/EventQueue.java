package com.vmlens.trace.agent.bootstrap.event;


import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class EventQueue implements QueueIn {

    private final TLinkedList<TLinkableWrapper<SerializableEvent>> queue;

    public EventQueue() {
        super();
        queue = new TLinkedList<>();
    }

    public synchronized void offer(SerializableEvent element) {
        queue.addFirst(wrap(element));
    }

    public synchronized SerializableEvent take() {
        TLinkableWrapper<SerializableEvent> result = queue.removeLast();
        if (result != null) {
            return result.element();
        }
        return null;
    }
}