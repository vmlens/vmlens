package com.vmlens.trace.agent.bootstrap.callback.threadlocal;


import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

import static com.vmlens.trace.agent.bootstrap.event.queue.EventQueueSingleton.eventQueue;

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

        ThreadLocalWhenInTest dataWhenInTest = threadLocal.getThreadLocalWhenInTest();
        if (dataWhenInTest != null) {
            callbackAction.execute(dataWhenInTest,eventQueueInternal);
        }
    }

    @Override
    public ThreadLocalForParallelize threadLocalForParallelize() {
        return threadLocalForParallelizeProvider.threadLocalForParallelize();
    }

    @Override
    public QueueIn eventQueue() {
        return eventQueueInternal;
    }

}