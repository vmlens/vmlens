package com.vmlens.trace.agent.bootstrap.parallelize.run.thread;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;

public class ThreadLocalWhenInTestAndSerializableEvents {

    private final ThreadLocalWhenInTest threadLocalWhenInTest;
    private final TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents;

    public ThreadLocalWhenInTestAndSerializableEvents(ThreadLocalWhenInTest threadLocalWhenInTest,
                                                      TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents) {
        this.threadLocalWhenInTest = threadLocalWhenInTest;
        this.serializableEvents = serializableEvents;
    }

    public static ThreadLocalWhenInTestAndSerializableEvents empty() {
        return new ThreadLocalWhenInTestAndSerializableEvents(null, emptyList());
    }


    public ThreadLocalWhenInTest threadLocalWhenInTest() {
        return threadLocalWhenInTest;
    }

    public TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents() {
        return serializableEvents;
    }
}
