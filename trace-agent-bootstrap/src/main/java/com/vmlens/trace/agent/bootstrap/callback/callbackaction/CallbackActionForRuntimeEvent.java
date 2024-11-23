package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;

public class CallbackActionForRuntimeEvent<EVENT extends RuntimeEvent> implements CallbackAction{

    private final EVENT runtimeEvent;
    private final SetFieldsStrategy setFieldsStrategy;

    public CallbackActionForRuntimeEvent(EVENT runtimeEvent, SetFieldsStrategy setFieldsStrategy) {
        this.runtimeEvent = runtimeEvent;
        this.setFieldsStrategy = setFieldsStrategy;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {

    }
}
