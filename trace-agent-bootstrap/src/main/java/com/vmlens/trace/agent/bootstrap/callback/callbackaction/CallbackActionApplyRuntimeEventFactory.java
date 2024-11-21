package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;

public class CallbackActionApplyRuntimeEventFactory implements CallbackAction {

    private final RuntimeEventFactory runtimeEventFactory;

    public CallbackActionApplyRuntimeEventFactory(RuntimeEventFactory runtimeEventFactory) {
        this.runtimeEventFactory = runtimeEventFactory;
    }

    public static void applyToRuntimeEvent(RuntimeEvent runtimeEvent,
                                           ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        SerializableEvent serializableEvent = threadLocalDataWhenInTest.after(runtimeEvent);
        if (serializableEvent != null) {
            queueIn.offer(serializableEvent);
        }
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        applyToRuntimeEvent(runtimeEventFactory.create(), threadLocalDataWhenInTest, queueIn);
    }

    // Visible for Test
    public RuntimeEventFactory runtimeEventFactory() {
        return runtimeEventFactory;
    }
}
