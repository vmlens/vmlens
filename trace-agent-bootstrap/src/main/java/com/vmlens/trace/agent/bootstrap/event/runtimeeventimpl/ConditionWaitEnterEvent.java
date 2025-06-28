package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.ConditionWaitEnterEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.ConditionWaitEnter;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.lock.LockEvent;
import com.vmlens.trace.agent.bootstrap.lock.LockType;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class ConditionWaitEnterEvent extends ConditionWaitEnterEventGen implements LockEvent {

    private final LockType lockTypeClass;
    private Object object;

    public ConditionWaitEnterEvent(LockType lockTypeClass, Object object) {
        this.lockTypeClass = lockTypeClass;
        this.object = object;
        this.lockType = lockTypeClass.id();
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

    public void setLockType(int lockType) {
        this.lockType = lockType;
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
        return new ConditionWaitEnter(this.threadIndex, lockTypeClass.create(objectHashCode));
    }

    @Override
    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
    }

    @Override
    public void setInMethodIdAndPosition(int inMethodId, int position, ReadWriteLockMap readWriteLockMap) {
        this.objectHashCode = lockTypeClass.getObjectHashCode(readWriteLockMap,System.identityHashCode(object));
        this.methodId = inMethodId;
        this.bytecodePosition = position;
        this.object = null;
    }
}
