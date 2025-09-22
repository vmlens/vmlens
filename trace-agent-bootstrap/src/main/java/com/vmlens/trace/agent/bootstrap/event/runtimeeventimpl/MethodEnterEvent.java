package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.MethodEnterEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;

public class MethodEnterEvent extends MethodEnterEventGen implements PluginEventOnly {

    public MethodEnterEvent(int methodId) {
        this.methodId = methodId;
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public int threadIndex() {
        return threadIndex;
    }

    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.incrementAndGetMethodCount();
    }

    public void setLoopId(int loopId) {
        this.loopId = loopId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public void setRunPosition(int runPosition) {
        // Nothing to do
    }

    @Override
    public int loopId() {
        return loopId;
    }

    @Override
    public int runId() {
        return runId;
    }
}
