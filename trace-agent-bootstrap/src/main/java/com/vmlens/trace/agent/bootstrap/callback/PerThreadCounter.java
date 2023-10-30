package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.event.SendEventContext;


public abstract class PerThreadCounter implements SendEventContext {
    private int methodCount;

    @Override
    public int incrementAndGetMethodCount() {
        return methodCount++;
    }
}
