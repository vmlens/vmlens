package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.gen.MethodEnterEventGen;

public class MethodEnterEvent extends MethodEnterEventGen implements RuntimeEvent {

    public MethodEnterEvent setSlidingWindowId(int slidingWindowId) {
        this.slidingWindowId = slidingWindowId;
        return this;
    }

    public MethodEnterEvent setThreadId(long threadId) {
        this.threadId = threadId;
        return this;
    }

    public MethodEnterEvent setMethodId(int methodId) {
        this.methodId = methodId;
        return this;
    }

    public MethodEnterEvent setMethodCounter(int methodCounter) {
        this.methodCounter = methodCounter;
        return this;
    }

    public MethodEnterEvent setLoopId(int loopId) {
        this.loopId = loopId;
        return this;
    }

    public MethodEnterEvent setRunId(int runId) {
        this.runId = runId;
        return this;
    }

    public MethodEnterEvent setRunPosition(int runPosition) {
        this.runPosition = runPosition;
        return this;
    }

    public int slidingWindowId() {
        return slidingWindowId;
    }

    public long threadId() {
        return threadId;
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

    public int runPosition() {
        return runPosition;
    }

    @Override
    public void accept(RuntimeEventVisitor visitor) {
        // Fixme implement
    }
}
