package com.vmlens.trace.agent.bootstrap.event;

public abstract class PerThreadCounter {
    private int methodCount;

    public int incrementAndGetMethodCount() {
        methodCount++;
        return methodCount;
    }

    public int methodCount() {
        return methodCount;
    }
}
