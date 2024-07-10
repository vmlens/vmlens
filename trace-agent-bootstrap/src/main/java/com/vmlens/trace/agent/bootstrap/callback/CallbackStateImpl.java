package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;


public class CallbackStateImpl {

    public void processRuntimeEventFactory(RuntimeEventFactory runtimeEventFactory,
                                           ThreadLocalForParallelize threadLocal,
                                           QueueIn queueIn) {
        ThreadLocalDataWhenInTest dataWhenInTest = threadLocal.startCallbackProcessing();
        if (dataWhenInTest != null) {
            try {
                SerializableEvent serializableEvent = dataWhenInTest.after(runtimeEventFactory.create());
                if (serializableEvent != null) {
                    queueIn.offer(serializableEvent);
                }

            } finally {
                dataWhenInTest.stopCallbackProcessing();
            }
        }
    }

}
