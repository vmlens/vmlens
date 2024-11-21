package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionCreateThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionSetStartedThread;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class ThreadStartCallbackImpl {

    private final ThreadLocalWhenInTestAdapter parallelizeBridgeForCallback;

    public ThreadStartCallbackImpl(ThreadLocalWhenInTestAdapter parallelizeBridgeForCallback) {
        this.parallelizeBridgeForCallback = parallelizeBridgeForCallback;
    }


    public void threadStart(Object newThread) {
        parallelizeBridgeForCallback.process(new CallbackActionSetStartedThread(
                new RunnableOrThreadWrapper(newThread)));
    }

    public void afterThreadStart() {
        parallelizeBridgeForCallback.process(new CallbackActionCreateThreadStartEvent());
    }
}
