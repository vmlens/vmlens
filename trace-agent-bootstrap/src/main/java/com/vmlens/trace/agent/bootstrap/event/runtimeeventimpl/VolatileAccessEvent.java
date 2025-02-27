package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.VolatileAccessEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

/**
 * a data structure
 */

public class VolatileAccessEvent extends VolatileAccessEventGen implements
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
    public InterleaveAction create() {
        return new VolatileFieldAccess(
                threadIndex,
                fieldId,
                operation);
    }


    @Override
    public void setInMethodIdAndPosition(int inMethodId, int position) {
        // Nothing To do
    }
}
