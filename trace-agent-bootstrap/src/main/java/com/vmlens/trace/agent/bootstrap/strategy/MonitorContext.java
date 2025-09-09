package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;

public class MonitorContext implements EventContext {

    private final Object object;
    private final int methodId;
    private final InTestActionProcessor inTestActionProcessor;

    public MonitorContext(Object object, int methodId, InTestActionProcessor inTestActionProcessor) {
        this.object = object;
        this.methodId = methodId;
        this.inTestActionProcessor = inTestActionProcessor;
    }

    @Override
    public Object object() {
        return object;
    }

    @Override
    public int methodId() {
        return methodId;
    }

    public InTestActionProcessor inTestActionProcessor() {
        return inTestActionProcessor;
    }
}
