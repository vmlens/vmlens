package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;

public class HasNextResult {

    private final boolean hasNext;
    private final SerializableEvent[] serializableEventArray;

    public HasNextResult(boolean hasNext, SerializableEvent[] serializableEventArray) {
        this.hasNext = hasNext;
        this.serializableEventArray = serializableEventArray;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public SerializableEvent[] serializableEventArray() {
        return serializableEventArray;
    }
}
