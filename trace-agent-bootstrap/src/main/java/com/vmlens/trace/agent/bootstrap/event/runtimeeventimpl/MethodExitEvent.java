package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.PluginEventOnly;
import com.vmlens.trace.agent.bootstrap.event.gen.MethodExitEventGen;


public class MethodExitEvent extends MethodExitEventGen implements PluginEventOnly {

    public MethodExitEvent(int methodId) {
        this.methodId = methodId;
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
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
    }

}
