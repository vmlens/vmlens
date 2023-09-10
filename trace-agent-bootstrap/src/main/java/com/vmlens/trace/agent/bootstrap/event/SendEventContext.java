package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.callback.QueueCollectionWrapper;

public class SendEventContext {

    public int incrementAndGetMethodCount() {
        return 0;
    }

    public QueueCollectionWrapper queueCollection() {
        return null;
    }

    public long threadId() {
        return 0L;
    }

    public int slidingWindowId() {
        return 0;
    }

}
