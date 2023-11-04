package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.event.gen.VolatileAccessEventGen;

import static com.vmlens.trace.agent.bootstrap.event.RuntimeEventIds.ID_SyncActions;

public class VolatileAccessEvent extends VolatileAccessEventGen implements RuntimeEvent {


    public VolatileAccessEvent(long threadId, int order, int fieldId, int methodId,
                               int operation, long objectHashCode) {
        this.threadId = threadId;
        this.order = order;
        this.fieldId = fieldId;
        this.methodId = methodId;
        this.operation = operation;
        this.objectHashCode = objectHashCode;
    }

    @Override
    public void send(SendEventContext context) {
        context.threadLocalWrapper().put(ID_SyncActions, this, slidingWindowId);
    }
}
