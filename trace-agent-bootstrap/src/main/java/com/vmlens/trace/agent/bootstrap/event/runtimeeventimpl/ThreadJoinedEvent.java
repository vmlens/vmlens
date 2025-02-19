package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.ThreadJoinedEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class ThreadJoinedEvent extends ThreadJoinedEventGen implements InterleaveActionFactory {


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

    @Override
    public InterleaveAction create() {
        return null;
    }
}
