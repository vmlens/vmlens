package com.vmlens.trace.agent.bootstrap.event;

public interface ThreadLocalWrapperForEvent {

    long threadId();

    void put(int index, StaticEvent element, int slidingWindowId);

    int incrementAndGetMethodCount();
}
