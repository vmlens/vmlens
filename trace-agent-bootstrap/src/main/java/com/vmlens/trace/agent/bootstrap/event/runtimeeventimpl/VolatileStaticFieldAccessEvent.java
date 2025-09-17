package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.VolatileFieldAccessEventStaticGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactoryAndRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.VolatileAccess;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileStaticFieldKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class VolatileStaticFieldAccessEvent extends VolatileFieldAccessEventStaticGen  implements
        InterleaveActionFactoryAndRuntimeEvent, WithInMethodIdPositionReadWriteLockMap {

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }


    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public void setOperation(int operation) {
        this.operation = operation;
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

    public void setBytecodePosition(int bytecodePosition) {
        this.bytecodePosition = bytecodePosition;
    }

    @Override
    public InterleaveAction create(CreateInterleaveActionContext context) {
        return new VolatileAccess(
                threadIndex,
                new VolatileStaticFieldKey(fieldId),
                operation);
    }

    @Override
    public void setInMethodIdAndPosition(int inMethodId, int position, ReadWriteLockMap readWriteLockMap) {
        // Nothing To do
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
