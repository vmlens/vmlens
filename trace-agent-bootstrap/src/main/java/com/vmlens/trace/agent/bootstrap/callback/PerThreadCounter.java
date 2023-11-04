package com.vmlens.trace.agent.bootstrap.callback;

public abstract class PerThreadCounter {
    private int methodCount;

    public int incrementAndGetMethodCount() {
        return methodCount++;
    }
}
