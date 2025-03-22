package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

public class VolatileFieldAccessEventForTest extends VolatileFieldAccessEvent {

    public int threadIndex() {
        return threadIndex;
    }


    public int bytecodePosition() {
        return bytecodePosition;
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

    public void setMethodCounter(int methodCounter) {
        this.methodCounter = methodCounter;
    }
}
