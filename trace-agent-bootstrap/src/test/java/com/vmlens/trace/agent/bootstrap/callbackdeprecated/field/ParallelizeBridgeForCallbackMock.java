package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.CallbackActionApplyRuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ParallelizeBridgeForCallback;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;

public class ParallelizeBridgeForCallbackMock implements ParallelizeBridgeForCallback {

    private RuntimeEventFactory runtimeEventFactory;

    @Override
    public void process(CallbackAction callbackAction) {
        runtimeEventFactory = ((CallbackActionApplyRuntimeEventFactory) callbackAction).runtimeEventFactory();
    }

    public RuntimeEventFactory runtimeEventFactory() {
        return runtimeEventFactory;
    }


}
