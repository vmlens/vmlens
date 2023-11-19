package com.vmlens.trace.agent.bootstrap.event;

public interface ThreadLocalWrapperForEvent {

    long threadId();

    void offer(StaticEvent element);

    int incrementAndGetMethodCount();
}
