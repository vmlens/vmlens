package com.vmlens.trace.agent.bootstrap.event;

public interface SendEventContext {

    int incrementAndGetMethodCount();

    long threadId();

    void put(int index, StaticEvent element, int slidingWindowId);

}
