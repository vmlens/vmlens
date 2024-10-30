package com.vmlens.trace.agent.bootstrap.callbackdeprecated.method;

import com.vmlens.trace.agent.bootstrap.event.impl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;

public class RuntimeEventFactoryMethodExit implements RuntimeEventFactory {
    private final int methodId;

    public RuntimeEventFactoryMethodExit(int methodId) {
        this.methodId = methodId;
    }

    @Override
    public RuntimeEvent create() {
        return new MethodExitEvent(methodId);
    }
}
