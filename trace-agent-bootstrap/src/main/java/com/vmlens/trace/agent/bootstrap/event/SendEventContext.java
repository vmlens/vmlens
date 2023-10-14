package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.callback.QueueCollectionWrapper;

public interface SendEventContext {

    int incrementAndGetMethodCount();

    long threadId();

    int slidingWindowId();

    void put(int index, StaticEvent element, int slidingWindowId);

}
