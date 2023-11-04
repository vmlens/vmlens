package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.event.gen.MethodEnterEventGen;

import static com.vmlens.trace.agent.bootstrap.event.RuntimeEventIds.ID_Method;

public class MethodEnterRuntimeEvent extends MethodEnterEventGen implements RuntimeEvent {

    public MethodEnterRuntimeEvent(int methodId) {
        this.methodId = methodId;
    }

    @Override
    public void send(SendEventContext context) {
        this.methodCounter = context.threadLocalWrapper().incrementAndGetMethodCount();
        this.threadId = context.threadLocalWrapper().threadId();

        context.threadLocalWrapper().put(ID_Method, this, this.slidingWindowId);
    }
}
