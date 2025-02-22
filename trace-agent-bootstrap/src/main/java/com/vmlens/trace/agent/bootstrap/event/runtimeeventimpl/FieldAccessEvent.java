package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.FieldAccessEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;

public class FieldAccessEvent extends FieldAccessEventGen implements
        PluginEventOnly, WithObjectHashCode {

    @Override
    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    @Override
    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    @Override
    public void setObjectHashCode(long objectHashCode) {
        this.objectHashCode = objectHashCode;
    }

    @Override
    public void setLoopId(int loopId) {
        this.loopId = loopId;
    }

    @Override
    public void setRunId(int runId) {
        this.runId = runId;
    }

    @Override
    public void setRunPosition(int runPosition) {
        this.runPosition = runPosition;
    }

}
