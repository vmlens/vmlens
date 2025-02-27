package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.MonitorEnterEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockOrMonitorEnter;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.Monitor;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class MonitorEnterEvent extends MonitorEnterEventGen implements
        InterleaveActionFactory, WithObjectHashCodeAndOrder<ObjectHashCode> {

    public MonitorEnterEvent(int methodId, int bytecodePosition) {
        this.methodId = methodId;
        this.bytecodePosition = bytecodePosition;
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    @Override
    public ObjectHashCode keyForOrderMap() {
        return new ObjectHashCode(objectHashCode);
    }

    public void setOrder(int order) {
        this.order = order;
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
        Monitor monitor = new Monitor(objectHashCode);
        return new LockOrMonitorEnter(threadIndex, monitor);
    }

    @Override
    public void setObjectHashCode(long objectHashCode) {
        this.objectHashCode = objectHashCode;
    }
}
