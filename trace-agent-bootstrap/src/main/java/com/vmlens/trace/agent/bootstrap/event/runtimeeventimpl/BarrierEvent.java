package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.BarrierEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.LockExitOrWaitEvent;
import com.vmlens.trace.agent.bootstrap.barriertype.BarrierKeyType;
import com.vmlens.trace.agent.bootstrap.barriertype.BarrierKeyTypeCollection;
import com.vmlens.trace.agent.bootstrap.barriertype.BarrierType;
import com.vmlens.trace.agent.bootstrap.barriertype.BarrierTypeCollection;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class BarrierEvent extends BarrierEventGen implements LockExitOrWaitEvent, WithInMethodIdPositionReadWriteLockMap {

    private final BarrierType barrierTypeClass;
    private final BarrierKeyType barrierKeyTypeClass;
    private Object object;

    public BarrierEvent(BarrierType barrierType,
                        BarrierKeyType barrierKeyTypeClass,
                        Object object) {
        this.barrierTypeClass = barrierType;
        this.barrierKeyTypeClass = barrierKeyTypeClass;
        this.object = object;
        this.barrierType = BarrierTypeCollection.SINGLETON.toId(barrierType);
        this.barrierKeyType = BarrierKeyTypeCollection.SINGLETON.toId(barrierKeyTypeClass);
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
    public Integer waitingThreadIndex() {
        return barrierTypeClass.asWaitingThreadIndex(threadIndex);
    }

    @Override
    public InterleaveAction create(CreateInterleaveActionContext context) {
        return barrierTypeClass.create(threadIndex,barrierKeyTypeClass.create(objectHashCode));
    }

    @Override
    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
    }

    @Override
    public void setInMethodIdAndPosition(int inMethodId, int position, ReadWriteLockMap readWriteLockMap) {
        this.objectHashCode = System.identityHashCode(object);
        this.methodId = inMethodId;
        this.bytecodePosition = position;
        this.object = null;
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
