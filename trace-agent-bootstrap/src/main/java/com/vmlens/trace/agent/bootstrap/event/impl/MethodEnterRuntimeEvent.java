package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.event.gen.MethodEnterEventGen;

public class MethodEnterRuntimeEvent extends MethodEnterEventGen implements RuntimeEvent {

    public MethodEnterRuntimeEvent(int methodId) {
        this.methodId = methodId;
    }

    @Override
    public void send(SendEventContext context) {
        this.methodCounter = context.incrementAndGetMethodCount();
        this.slidingWindowId = context.slidingWindowId();
        this.threadId = context.threadId();

        context.queueCollection().put(ID_Method, this, this.slidingWindowId);
    }
}
