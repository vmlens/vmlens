package com.vmlens.trace.agent.bootstrap.event;

public interface ThreadLocalWrapperForEvent {

    long threadId();

    void offer(SerializableEvent element);

    int incrementAndGetMethodCount();
}
