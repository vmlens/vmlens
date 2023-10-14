package com.vmlens.trace.agent.bootstrap.event;

public interface QueueIn {

    void put(int index, StaticEvent element, int slidingWindowId);

    void putDirect(StaticEvent in);
}
