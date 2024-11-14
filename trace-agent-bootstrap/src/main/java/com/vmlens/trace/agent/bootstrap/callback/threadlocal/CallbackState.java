package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class CallbackState {
    public void process(CallbackAction callbackAction,
                                           ThreadLocalForParallelize threadLocal,
                                           QueueIn queueIn) {
        ThreadLocalDataWhenInTest dataWhenInTest = threadLocal.startCallbackProcessing();
        if (dataWhenInTest != null) {
            try {
                callbackAction.execute(dataWhenInTest, queueIn);
            } finally {
                dataWhenInTest.stopCallbackProcessing();
            }
        }
    }
}
