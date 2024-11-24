package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.gen.ThreadStartEventGen;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class ThreadStartEvent extends ThreadStartEventGen implements RuntimeEvent {

    private RunnableOrThreadWrapper startedThread;

    public ThreadStartEvent(RunnableOrThreadWrapper startedThread) {
        this.startedThread = startedThread;
    }

    public int threadIndex() {
        return threadIndex;
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public int methodCounter() {
        return methodCounter;
    }

    public void setMethodCounter(int methodCounter) {
        this.methodCounter = methodCounter;
    }

    public int startedThreadIndex() {
        return startedThreadIndex;
    }

    public void setStartedThreadIndex(int startedThreadIndex) {
        this.startedThreadIndex = startedThreadIndex;
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

    public RunnableOrThreadWrapper startedThread() {
        return startedThread;
    }

    public void setStartedThreadToNull() {
        startedThread = null;
    }

    @Override
    public void accept(RuntimeEventVisitor visitor) {
        visitor.visit(this);
    }
}
