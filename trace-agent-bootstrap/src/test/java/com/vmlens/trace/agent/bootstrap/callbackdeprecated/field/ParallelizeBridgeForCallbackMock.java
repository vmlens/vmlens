package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.CallbackAction;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.CallbackActionApplyRuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.ParallelizeBridgeForCallback;

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
