package com.vmlens.trace.agent.bootstrap.callbackdeprecated;


import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

public class ThreadStartCallbackImpl {

    private final ThreadLocalWhenInTestAdapter parallelizeBridgeForCallback;

    public ThreadStartCallbackImpl(ThreadLocalWhenInTestAdapter parallelizeBridgeForCallback) {
        this.parallelizeBridgeForCallback = parallelizeBridgeForCallback;
    }


    public void threadStart(Object newThread) {

    }

    public void afterThreadStart() {

    }
}
