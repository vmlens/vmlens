package com.vmlens.trace.agent.bootstrap.callback.method;

import com.vmlens.trace.agent.bootstrap.event.impl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;

public class RuntimeEventFactoryMethodEnter implements RuntimeEventFactory {

    private final int methodId;

    public RuntimeEventFactoryMethodEnter(int methodId) {
        this.methodId = methodId;
    }

    @Override
    public RuntimeEvent create() {
        return new MethodEnterEvent(methodId);
    }
}
