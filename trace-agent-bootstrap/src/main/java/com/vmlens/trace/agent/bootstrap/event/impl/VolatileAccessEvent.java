package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.event.gen.VolatileAccessEventGen;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainer;


public class VolatileAccessEvent extends VolatileAccessEventGen implements RuntimeEvent {

    // Fixme


    @Override
    public int threadIndex() {
        return 0;
    }

    @Override
    public void addToContainer(int positionInThread, BlockBuilderAndCalculatedRunElementContainer container) {

    }

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
        context.threadLocalWrapper().offer(this);
    }

    // Currently not used
    VolatileAccessEvent setSlidingWindowId(int slidingWindowId) {
        this.slidingWindowId = slidingWindowId;
        return this;
    }

    // For Test
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

    public int slidingWindowId() {
        return slidingWindowId;
    }

    public long threadId() {
        return threadId;
    }

    public int programCounter() {
        return programCounter;
    }

    public int order() {
        return order;
    }

    public int fieldId() {
        return fieldId;
    }

    public int methodCounter() {
        return methodCounter;
    }

    public int methodId() {
        return methodId;
    }

    public int operation() {
        return operation;
    }

    public long objectHashCode() {
        return objectHashCode;
    }

    public int loopId() {
        return loopId;
    }

    public int runId() {
        return runId;
    }

    public int runPosition() {
        return runPosition;
    }

}
