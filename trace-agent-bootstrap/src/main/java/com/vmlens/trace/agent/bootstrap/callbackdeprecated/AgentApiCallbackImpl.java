package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.HasNextResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade.parallelize;

public class AgentApiCallbackImpl {

    public boolean hasNext(Object obj) {
        return hasNext(obj, ThreadLocalWhenInTestAdapterImpl.eventQueue, ThreadLocalWhenInTestAdapterImpl.callbackStatePerThread.get());
    }

    public boolean hasNext(Object obj, QueueIn queueIn, ThreadLocalForParallelize callbackStatePerThread) {
        HasNextResult result = parallelize().hasNext(callbackStatePerThread, obj);

        for (SerializableEvent event : result.serializableEventArray()) {
            queueIn.offer(event);
        }
        return result.hasNext();
    }
}
