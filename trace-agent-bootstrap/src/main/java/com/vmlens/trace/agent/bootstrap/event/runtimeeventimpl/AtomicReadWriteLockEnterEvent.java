package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.AtomicReadWriteLockEnterEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactoryAndRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.LockEnterImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.MethodIdByteCodePositionAndThreadIndex;
import com.vmlens.trace.agent.bootstrap.lock.LockType;

public class AtomicReadWriteLockEnterEvent extends AtomicReadWriteLockEnterEventGen implements
        InterleaveActionFactoryAndRuntimeEvent, WithInMethodIdPositionObjectHashCode {

    private final LockType lockTypeClass;


    public AtomicReadWriteLockEnterEvent(LockType lockTypeClass) {
        this.lockTypeClass = lockTypeClass;
        this.lockType = lockTypeClass.id();
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
        return new LockEnterImpl(new MethodIdByteCodePositionAndThreadIndex(methodId, bytecodePosition, threadIndex),
                lockTypeClass.create(objectHashCode),
                lockTypeClass.isRead());
    }


    @Override
    public void setInMethodIdPositionObjectHashCode(int inMethodId, int position, long objectHashCode) {
        this.objectHashCode = objectHashCode;
        this.methodId = inMethodId;
        this.bytecodePosition = position;

    }

    //  For Tests
    public void setObjectHashCode(long objectHashCode) {
        this.objectHashCode = objectHashCode;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public void setBytecodePosition(int bytecodePosition) {
        this.bytecodePosition = bytecodePosition;
    }

    public void setAtomicMethodId(int atomicMethodId) {
        this.atomicMethodId = atomicMethodId;
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
    public boolean startsNewThread() {
        return false;
    }

}
