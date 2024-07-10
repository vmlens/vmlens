package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.gen.VolatileAccessEventGen;

/**
 * a data structure
 */

public class VolatileAccessEvent extends VolatileAccessEventGen implements RuntimeEvent {

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public void setObjectHashCode(long objectHashCode) {
        this.objectHashCode = objectHashCode;
    }

    public void setMethodCounter(int methodCounter) {
        this.methodCounter = methodCounter;
    }

    public void setLoopId(int loopId) {
        this.loopId = loopId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public void setRunPosition(int runPosition) {
        this.runPosition = runPosition;
    }

    public int threadIndex() {
        return threadIndex;
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

    @Override
    public void accept(RuntimeEventVisitor visitor) {
        visitor.visit(this);
    }


}
