package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.event.gen.VolatileAccessEventGen;

import static com.vmlens.trace.agent.bootstrap.event.RuntimeEventIds.ID_SyncActions;

public class VolatileAccessEvent extends VolatileAccessEventGen implements RuntimeEvent {
    public VolatileAccessEvent setThreadId(long threadId) {
        this.threadId = threadId;
        return this;
    }

    public VolatileAccessEvent setOrder(int order) {
        this.order = order;
        return this;
    }

    public VolatileAccessEvent setFieldId(int fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    public VolatileAccessEvent setMethodId(int methodId) {
        this.methodId = methodId;
        return this;
    }

    public VolatileAccessEvent setOperation(int operation) {
        this.operation = operation;
        return this;
    }

    public VolatileAccessEvent setObjectHashCode(long objectHashCode) {
        this.objectHashCode = objectHashCode;
        return this;
    }

    @Override
    public void send(SendEventContext context) {
        context.threadLocalWrapper().put(ID_SyncActions, this, slidingWindowId);
    }

    // For Test
    VolatileAccessEvent setSlidingWindowId(int slidingWindowId) {
        this.slidingWindowId = slidingWindowId;
        return this;
    }

    VolatileAccessEvent setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
        return this;
    }

    VolatileAccessEvent setMethodCounter(int methodCounter) {
        this.methodCounter = methodCounter;
        return this;
    }

    VolatileAccessEvent setLoopId(int loopId) {
        this.loopId = loopId;
        return this;
    }

    VolatileAccessEvent setRunId(int runId) {
        this.runId = runId;
        return this;
    }

    VolatileAccessEvent setRunPosition(int runPosition) {
        this.runPosition = runPosition;
        return this;
    }

}
