package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.CallbackActionCreateThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.CallbackActionSetStartedThread;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.ParallelizeBridgeForCallback;

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
