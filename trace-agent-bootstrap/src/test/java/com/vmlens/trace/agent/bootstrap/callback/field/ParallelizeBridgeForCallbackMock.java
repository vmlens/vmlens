package com.vmlens.trace.agent.bootstrap.callback.field;

import com.vmlens.trace.agent.bootstrap.event.impl.ParallelizeBridgeForCallback;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;

public class ParallelizeBridgeForCallbackMock implements ParallelizeBridgeForCallback {

    private RuntimeEventFactory runtimeEventFactory;

    @Override
    public void processRuntimeEventFactory(RuntimeEventFactory runtimeEventFactory) {
        this.runtimeEventFactory = runtimeEventFactory;
    }

    public RuntimeEventFactory runtimeEventFactory() {
        return runtimeEventFactory;
    }
}
