package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.MethodExitEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;


public class MethodExitEvent extends MethodExitEventGen implements PluginEventOnly {

    public MethodExitEvent() {
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public int threadIndex() {
        return threadIndex;
    }

    public void setCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.incrementAndGetMethodCount();
        this.dominatorTreeCounter = perThreadCounter.incrementDominatorTreeAndGetMiddle();
    }

    public void setLoopId(int loopId) {
        this.loopId = loopId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public void setRunPosition(int runPosition) {
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
