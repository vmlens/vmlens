package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.barrierkeytype.BarrierKeyType;
import com.vmlens.trace.agent.bootstrap.barrierkeytype.BarrierKeyTypeCollection;
import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.BarrierGetStateEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.NoThreadOperationFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.MethodIdByteCodePositionAndThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.BarrierReadState;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class BarrierGetStateEvent extends BarrierGetStateEventGen implements NoThreadOperationFactory,
        WithInMethodIdPositionReadWriteLockMap {


    private final BarrierKeyType barrierKeyTypeClass;
    private Object object;

    public BarrierGetStateEvent(BarrierKeyType barrierKeyTypeClass, Object object) {
        this.barrierKeyTypeClass = barrierKeyTypeClass;
        this.barrierKeyType = BarrierKeyTypeCollection.SINGLETON.toId(barrierKeyTypeClass);
        this.object = object;
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

    @Override
    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    @Override
    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
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
    public InterleaveAction create(CreateInterleaveActionContext context) {
        return new BarrierReadState(new MethodIdByteCodePositionAndThreadIndex(methodId, bytecodePosition, threadIndex),
                barrierKeyTypeClass.create(objectHashCode));
    }
}