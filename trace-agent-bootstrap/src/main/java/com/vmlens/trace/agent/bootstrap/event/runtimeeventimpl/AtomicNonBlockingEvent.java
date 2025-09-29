package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.AtomicNonBlockingEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactoryAndRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.MethodIdByteCodePositionAndThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.VolatileAccess;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.AtomicNonBlockingKey;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class AtomicNonBlockingEvent extends AtomicNonBlockingEventGen implements
        InterleaveActionFactoryAndRuntimeEvent, WithInMethodIdPositionReadWriteLockMap {

    private Object object;

    public AtomicNonBlockingEvent(int operation, Object object) {
        this.operation = operation;
        this.object = object;
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
        return new VolatileAccess(
                new MethodIdByteCodePositionAndThreadIndex(methodId, bytecodePosition, threadIndex),
                new AtomicNonBlockingKey(objectHashCode),
                operation);
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

    @Override
    public boolean startsNewThread() {
        return false;
    }
}