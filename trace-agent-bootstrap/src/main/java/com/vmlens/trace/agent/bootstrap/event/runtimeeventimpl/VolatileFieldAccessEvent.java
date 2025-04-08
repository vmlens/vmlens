package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.VolatileFieldAccessEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileAccess;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.volatileaccesskey.VolatileFieldKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;


public class VolatileFieldAccessEvent extends VolatileFieldAccessEventGen implements
        InterleaveActionFactory, WithObjectHashCode, WithInMethodIdAndPosition {

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

    public void setObjectHashCode(long objectHashCode) {
        this.objectHashCode = objectHashCode;
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
        return new VolatileAccess(
                threadIndex,
                new VolatileFieldKey(fieldId,objectHashCode),
                operation);
    }


    @Override
    public void setInMethodIdAndPosition(int inMethodId, int position, ReadWriteLockMap readWriteLockMap) {
        // Nothing To do
    }
}
