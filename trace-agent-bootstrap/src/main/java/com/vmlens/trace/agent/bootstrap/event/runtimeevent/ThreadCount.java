package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

public interface ThreadCount {
    void decrement(int i);

    void increment();
}
