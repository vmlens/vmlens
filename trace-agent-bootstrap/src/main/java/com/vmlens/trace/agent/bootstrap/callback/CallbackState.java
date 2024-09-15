package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.impl.ParallelizeBridgeForCallback;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class CallbackState implements ParallelizeBridgeForCallback {

    public static final EventQueue eventQueue = new EventQueue();
    private final CallbackStateImpl callbackStateImpl = new CallbackStateImpl();

    private final ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider;
    private final QueueIn eventQueueInternal;

    public CallbackState() {
        this(new ThreadLocalForParallelizeProviderImpl(), eventQueue);
    }

    public CallbackState(ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider,
                         QueueIn eventQueueInternal) {
        this.threadLocalForParallelizeProvider = threadLocalForParallelizeProvider;
        this.eventQueueInternal = eventQueueInternal;
    }

    // Fixme  make private and move to ThreadLocalForParallelizeProviderImpl
    public static final ThreadLocal<ThreadLocalForParallelize> callbackStatePerThread = new ThreadLocal<ThreadLocalForParallelize>() {
        @Override
        protected ThreadLocalForParallelize initialValue() {
            return new ThreadLocalForParallelize(Thread.currentThread().getId());
        }
    };

    @Override
    public void processRuntimeEventFactory(RuntimeEventFactory runtimeEventFactory) {
        callbackStateImpl.processRuntimeEventFactory(runtimeEventFactory,
                threadLocalForParallelizeProvider.threadLocalForParallelize(), eventQueueInternal);
    }
}
