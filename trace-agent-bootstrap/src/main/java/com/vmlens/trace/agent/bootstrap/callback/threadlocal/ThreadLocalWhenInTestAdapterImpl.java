package com.vmlens.trace.agent.bootstrap.callback.threadlocal;


import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.EventQueue;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.ThreadLocalForParallelizeProviderImpl;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ThreadLocalWhenInTestAdapterImpl implements ThreadLocalWhenInTestAdapter {

    public static final EventQueue eventQueue = new EventQueue();

    private final ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider;
    private final QueueIn eventQueueInternal;

    public ThreadLocalWhenInTestAdapterImpl() {
        this(new ThreadLocalForParallelizeProviderImpl(), eventQueue);
    }

    public ThreadLocalWhenInTestAdapterImpl(ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider,
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
        ThreadLocalForParallelize threadLocal = threadLocalForParallelizeProvider
                .threadLocalForParallelize();

        ThreadLocalWhenInTest dataWhenInTest = threadLocal.startCallbackProcessing();
        if (dataWhenInTest != null) {
            try {
                TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents =
                        callbackAction.execute(dataWhenInTest);
                for (TLinkableWrapper<SerializableEvent> event : serializableEvents) {
                    eventQueue.offer(event.element);
                }
            } finally {
                dataWhenInTest.stopCallbackProcessing();
            }
        }
    }
}