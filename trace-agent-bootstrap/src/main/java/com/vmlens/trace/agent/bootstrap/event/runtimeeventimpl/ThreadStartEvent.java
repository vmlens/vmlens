package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.ThreadStartEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class ThreadStartEvent extends ThreadStartEventGen implements InterleaveActionFactory, WithInMethodIdAndPosition {

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
    }

    public void setStartedThreadIndex(int startedThreadIndex) {
        this.startedThreadIndex = startedThreadIndex;
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
        return new ThreadStart(threadIndex, startedThreadIndex);
    }

    @Override
    public void setInMethodIdAndPosition(int inMethodId, int position) {
        this.methodId = inMethodId;
        this.bytecodePosition = position;
    }

}
