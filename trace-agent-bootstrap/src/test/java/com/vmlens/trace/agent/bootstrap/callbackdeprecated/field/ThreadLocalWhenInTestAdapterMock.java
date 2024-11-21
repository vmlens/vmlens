package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionApplyRuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;

public class ThreadLocalWhenInTestAdapterMock implements ThreadLocalWhenInTestAdapter {

    private RuntimeEventFactory runtimeEventFactory;

    @Override
    public void process(CallbackAction callbackAction) {
        runtimeEventFactory = ((CallbackActionApplyRuntimeEventFactory) callbackAction).runtimeEventFactory();
    }

    public RuntimeEventFactory runtimeEventFactory() {
        return runtimeEventFactory;
    }


}
