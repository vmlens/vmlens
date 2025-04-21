package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.HasNextResult;

import static com.vmlens.trace.agent.bootstrap.event.queue.EventQueueSingleton.eventQueue;
import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.callbackStatePerThread;

public class VmlensApiCallbackImpl {

    public void close(Object obj) {
        ParallelizeFacade.parallelize().close(callbackStatePerThread.get(), obj);
    }

    public boolean hasNext(Object obj) {
        HasNextResult result = ParallelizeFacade.parallelize().hasNext(callbackStatePerThread.get(), obj);
        eventQueue.offer(result.serializableEvents());
        return result.hasNext();
    }
}
