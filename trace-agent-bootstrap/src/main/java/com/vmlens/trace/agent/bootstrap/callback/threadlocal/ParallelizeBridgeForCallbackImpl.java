package com.vmlens.trace.agent.bootstrap.callback.threadlocal;


import com.vmlens.trace.agent.bootstrap.callbackdeprecated.EventQueue;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.ThreadLocalForParallelizeProviderImpl;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class ParallelizeBridgeForCallbackImpl implements ParallelizeBridgeForCallback {

    public static final EventQueue eventQueue = new EventQueue();
    private final CallbackState callbackState = new CallbackState();

    private final ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider;
    private final QueueIn eventQueueInternal;

    public ParallelizeBridgeForCallbackImpl() {
        this(new ThreadLocalForParallelizeProviderImpl(), eventQueue);
    }

    public ParallelizeBridgeForCallbackImpl(ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider,
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
    public void process(CallbackAction callbackAction) {
        callbackState.process(callbackAction,
                threadLocalForParallelizeProvider.threadLocalForParallelize(),
                eventQueueInternal);
    }


}
