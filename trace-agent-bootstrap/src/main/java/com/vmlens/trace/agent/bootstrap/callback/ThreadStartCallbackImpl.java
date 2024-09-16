package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.event.impl.ParallelizeBridgeForCallback;

public class ThreadStartCallbackImpl {

    private final ParallelizeBridgeForCallback parallelizeBridgeForCallback;

    public ThreadStartCallbackImpl(ParallelizeBridgeForCallback parallelizeBridgeForCallback) {
        this.parallelizeBridgeForCallback = parallelizeBridgeForCallback;
    }

    public void threadStart(Object newThread) {
        parallelizeBridgeForCallback.processRuntimeEventFactory(new RuntimeEventFactoryThreadStart(newThread));
    }
}
