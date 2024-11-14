package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.CallbackActionCreateThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.CallbackActionSetStartedThread;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ParallelizeBridgeForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class ThreadStartCallbackImpl {

    private final ParallelizeBridgeForCallback parallelizeBridgeForCallback;

    public ThreadStartCallbackImpl(ParallelizeBridgeForCallback parallelizeBridgeForCallback) {
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
