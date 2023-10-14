package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.event.gen.ThreadJoinedEventGen;

public class ThreadJoinedEvent extends ThreadJoinedEventGen implements RuntimeEvent {

    @Override
    public void setRunLoopAndSlidingWindowId(int runId, int loopId, int slidingWindowId) {
        this.runId = runId;
        this.loopId = loopId;
        this.slidingWindowId = slidingWindowId;
    }

    @Override
    public void send(SendEventContext context) {

    }
}
