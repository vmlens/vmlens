package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.MonitorExitEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.NotThreadStartedInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.LockExit;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.MonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class MonitorExitEvent extends MonitorExitEventGen implements
        NotThreadStartedInterleaveActionFactory, WithObjectHashCode {

    public MonitorExitEvent(int methodId, int bytecodePosition) {
        this.methodId = methodId;
        this.bytecodePosition = bytecodePosition;
    }

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
    public InterleaveAction create(CreateInterleaveActionContext context) {
        return new LockExit(threadIndex, new MonitorKey(objectHashCode));
    }

    @Override
    public void setObjectHashCode(long objectHashCode) {
        this.objectHashCode = objectHashCode;
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
