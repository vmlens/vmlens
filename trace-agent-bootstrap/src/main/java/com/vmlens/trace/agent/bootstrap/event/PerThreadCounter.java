package com.vmlens.trace.agent.bootstrap.event;

public interface  PerThreadCounter {

    int incrementAndGetMethodCount();
    int methodCount();

    int dominatorTreeCount();
    int incrementDominatorTreeAndGetMiddle();
}
