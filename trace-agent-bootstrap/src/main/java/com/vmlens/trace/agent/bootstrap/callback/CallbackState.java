package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.event.impl.ParallelizeBridgeForCallback;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class CallbackState implements ParallelizeBridgeForCallback {

    public static final EventQueue eventQueue = new EventQueue();
    private final CallbackStateImpl callbackStateImpl = new CallbackStateImpl();

    // Fixme  make private
    public static final ThreadLocal<ThreadLocalForParallelize> callbackStatePerThread = new ThreadLocal<ThreadLocalForParallelize>() {
        @Override
        protected ThreadLocalForParallelize initialValue() {
            return new ThreadLocalForParallelize(Thread.currentThread().getId());
        }
    };


    @Override
    public void processRuntimeEventFactory(RuntimeEventFactory runtimeEventFactory) {
        callbackStateImpl.processRuntimeEventFactory(runtimeEventFactory,
                callbackStatePerThread.get(), eventQueue);
    }
}
