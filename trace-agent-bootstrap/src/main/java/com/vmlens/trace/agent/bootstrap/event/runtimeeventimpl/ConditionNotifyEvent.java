package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.ConditionNotifyEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;

public class ConditionNotifyEvent extends ConditionNotifyEventGen implements PluginEventOnly  {

    public ConditionNotifyEvent(ConditionNotifyEventType conditionNotifyEventTypeEnum) {
        this.conditionNotifyEventType = conditionNotifyEventTypeEnum.id();
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public void setMethodCounter(int methodCounter) {
        this.methodCounter = methodCounter;
    }

    public void setObjectHashCode(long objectHashCode) {
        this.objectHashCode = objectHashCode;
    }

    public void setBytecodePosition(int bytecodePosition) {
        this.bytecodePosition = bytecodePosition;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
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
    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
    }
}
