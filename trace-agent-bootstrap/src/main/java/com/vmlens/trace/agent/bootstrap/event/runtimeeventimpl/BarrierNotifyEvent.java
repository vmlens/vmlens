package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.barrierkeytype.BarrierKeyType;
import com.vmlens.trace.agent.bootstrap.barrierkeytype.BarrierKeyTypeCollection;
import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.BarrierNotifyEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.NextStateBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.BarrierNotify;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;

public class BarrierNotifyEvent extends BarrierNotifyEventGen
        implements WithInMethodIdPositionObjectHashCode, ExecuteBeforeEvent {

    private final BarrierKeyType barrierKeyTypeClass;

    public BarrierNotifyEvent(BarrierKeyType barrierKeyTypeClass) {
        this.barrierKeyTypeClass = barrierKeyTypeClass;
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

    public void setBarrierKeyType(int barrierKeyType) {
        this.barrierKeyType = barrierKeyType;
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
    public int loopId() {
        return loopId;
    }

    @Override
    public int runId() {
        return runId;
    }

    @Override
    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
    }

    @Override
    public InterleaveAction create(CreateInterleaveActionContext context) {
        return new BarrierNotify(threadIndex,barrierKeyTypeClass.create(objectHashCode));
    }

    @Override
    public void setInMethodIdPositionObjectHashCode(int inMethodId, int position, long objectHashCode) {
        this.objectHashCode = objectHashCode;
        this.methodId = inMethodId;
        this.bytecodePosition = position;
    }

    @Override
    public void addToBuilder(NextStateBuilder nextStateBuilder) {
        nextStateBuilder.addExitEvent();
    }

    @Override
    public boolean startsNewThread() {
        return false;
    }
}
