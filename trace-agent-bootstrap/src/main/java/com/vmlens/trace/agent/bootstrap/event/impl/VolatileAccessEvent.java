package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.event.gen.VolatileAccessEventGen;

import static com.vmlens.trace.agent.bootstrap.event.RuntimeEventIds.ID_SyncActions;

public class VolatileAccessEvent extends VolatileAccessEventGen implements RuntimeEvent {


    public VolatileAccessEvent(long threadId, int order, int fieldId, int methodCounter, int methodId,
                               int operation, long objectHashCode) {
        this.threadId = threadId;
        this.order = order;
        this.fieldId = fieldId;
        this.methodCounter = methodCounter;
        this.methodId = methodId;
        this.operation = operation;
        this.objectHashCode = objectHashCode;
    }

    @Override
    public void setRunLoopAndSlidingWindowId(int runId, int loopId, int slidingWindowId) {
        this.runId = runId;
        this.loopId = loopId;
        this.slidingWindowId = slidingWindowId;
    }

    @Override
    public void send(SendEventContext context) {
        context.put(ID_SyncActions, this, slidingWindowId);
    }
}
