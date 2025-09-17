package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.AtomicReadWriteLockExitEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactoryAndRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.LockExit;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.lock.LockType;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class AtomicReadWriteLockExitEvent extends AtomicReadWriteLockExitEventGen implements
        InterleaveActionFactoryAndRuntimeEvent, WithInMethodIdPositionReadWriteLockMap {

    private final LockType lockTypeClass;
    private Object object;

    public AtomicReadWriteLockExitEvent(LockType lockTypeClass, Object object) {
        this.lockTypeClass = lockTypeClass;
        this.object = object;
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
        return new LockExit(threadIndex, lockTypeClass.create(objectHashCode));
    }

     @Override
    public void setInMethodIdAndPosition(int inMethodId, int position, ReadWriteLockMap readWriteLockMap) {
        this.objectHashCode = System.identityHashCode(object);
        this.methodId = inMethodId;
        this.bytecodePosition = position;
        this.object = null;
    }


    public void setAtomicMethodId(int atomicMethodId) {
        this.atomicMethodId = atomicMethodId;
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

    @Override
    public int loopId() {
        return loopId;
    }

    @Override
    public int runId() {
        return runId;
    }

}
