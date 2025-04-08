package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.LockEnterEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockEnterImpl;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Lock;
import com.vmlens.trace.agent.bootstrap.interleave.lock.MonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.lock.LockEvent;
import com.vmlens.trace.agent.bootstrap.lock.LockType;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class LockEnterEvent extends LockEnterEventGen implements LockEvent {

    private final LockType lockType;
    private Object object;

    public LockEnterEvent(LockType lockType, Object object) {
        this.lockType = lockType;
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
        Lock monitor = new Lock(lockType.create(objectHashCode));
        return new LockEnterImpl(threadIndex, monitor);
    }

    @Override
    public void setInMethodIdAndPosition(int inMethodId, int position, ReadWriteLockMap readWriteLockMap) {
        this.objectHashCode = readWriteLockMap.getUnderlying(System.identityHashCode(object));
        this.methodId = inMethodId;
        this.bytecodePosition = position;
        this.object = null;
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
}
