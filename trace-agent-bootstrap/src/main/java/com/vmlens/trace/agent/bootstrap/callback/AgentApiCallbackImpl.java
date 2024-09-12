package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.HasNextResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade.parallelize;

public class AgentApiCallbackImpl {

    public boolean hasNext(Object obj) {
        return hasNext(obj, CallbackState.eventQueue, CallbackState.callbackStatePerThread.get());
    }

    // For Test
    public boolean hasNext(Object obj, QueueIn queueIn, ThreadLocalForParallelize callbackStatePerThread) {
        HasNextResult result = parallelize().hasNext(callbackStatePerThread, obj);

        for (TLinkableWrapper<SerializableEvent> event : result.serializableEventList()) {
            queueIn.offer(event.element);
        }
        return result.hasNext();
    }
}
