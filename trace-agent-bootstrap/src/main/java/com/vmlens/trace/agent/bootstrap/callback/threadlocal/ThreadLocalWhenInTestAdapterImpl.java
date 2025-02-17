package com.vmlens.trace.agent.bootstrap.callback.threadlocal;


import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.event.EventQueueSingleton.eventQueue;

public class ThreadLocalWhenInTestAdapterImpl implements ThreadLocalWhenInTestAdapter {

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


    @Override
    public void process(CallbackAction callbackAction) {
        ThreadLocalForParallelize threadLocal = threadLocalForParallelizeProvider
                .threadLocalForParallelize();

        ThreadLocalWhenInTest dataWhenInTest = threadLocal.startCallbackProcessing();
        if (dataWhenInTest != null) {
            try {
                TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents =
                        callbackAction.execute(dataWhenInTest);
                eventQueueInternal.offer(serializableEvents);
            } finally {
                threadLocal.stopCallbackProcessing();
            }
        }
    }

    @Override
    public ThreadLocalForParallelize threadLocalForParallelize() {
        return threadLocalForParallelizeProvider.threadLocalForParallelize();
    }
}