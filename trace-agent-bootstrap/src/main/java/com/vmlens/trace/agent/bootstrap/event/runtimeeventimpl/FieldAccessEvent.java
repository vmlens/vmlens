package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.FieldAccessEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class FieldAccessEvent extends FieldAccessEventGen implements
        PluginEventOnly, WithObjectHashCode , EitherVolatileOrNormalFieldAccessEvent {

    @Override
    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public void setCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
        this.dominatorTreeCounter = perThreadCounter.dominatorTreeCount();

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

    @Override
    public int loopId() {
        return loopId;
    }

    @Override
    public int runId() {
        return runId;
    }


    @Override
    public void setInMethodIdAndPosition(int inMethodId, int position, ReadWriteLockMap readWriteLockMap) {
        /*
         * nothing to do
         * reuse of the set logic
         * used for reflection and atomic field updater
         * method id and object hash code gets set in the strategy
         */
    }
}
