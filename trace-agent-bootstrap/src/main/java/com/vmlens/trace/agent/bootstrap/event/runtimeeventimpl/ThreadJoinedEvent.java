package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.gen.ThreadJoinedEventGen;

public class ThreadJoinedEvent extends ThreadJoinedEventGen implements RuntimeEvent {


    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
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

}
