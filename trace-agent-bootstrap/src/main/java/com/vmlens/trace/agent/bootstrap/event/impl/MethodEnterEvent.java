package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.gen.MethodEnterEventGen;

public class MethodEnterEvent extends MethodEnterEventGen implements RuntimeEvent {


    public int threadIndex() {
        return threadIndex;
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public int methodId() {
        return methodId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public int methodCounter() {
        return methodCounter;
    }

    public void setMethodCounter(int methodCounter) {
        this.methodCounter = methodCounter;
    }

    public int loopId() {
        return loopId;
    }

    public void setLoopId(int loopId) {
        this.loopId = loopId;
    }

    public int runId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public int runPosition() {
        return runPosition;
    }

    public void setRunPosition(int runPosition) {
        this.runPosition = runPosition;
    }


    @Override
    public void accept(RuntimeEventVisitor visitor) {
        // Fixme implement
    }
}
