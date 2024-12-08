package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

public class MethodEnterEventForTest extends MethodEnterEvent {

    public MethodEnterEventForTest(int methodId) {
        super(methodId);
    }

    public int threadIndex() {
        return threadIndex;
    }

    public int methodId() {
        return methodId;
    }

    public int methodCounter() {
        return methodCounter;
    }

    public int loopId() {
        return loopId;
    }

    public int runId() {
        return runId;
    }

    public void setMethodCounter(int methodCounter) {
        this.methodCounter = methodCounter;
    }

}
