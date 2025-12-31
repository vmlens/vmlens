package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.GetLockStateEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.NoThreadOperationFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lock.GetLockState;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.MethodIdByteCodePositionAndThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.StampedLockKey;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class GetLockStateEvent extends GetLockStateEventGen implements NoThreadOperationFactory,
        WithInMethodIdPositionReadWriteLockMap {
    
    private Object object;

    public GetLockStateEvent(Object object) {
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
    public void setCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
        this.dominatorTreeCounter = perThreadCounter.dominatorTreeCount();
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
        return new GetLockState(new MethodIdByteCodePositionAndThreadIndex(methodId, bytecodePosition, threadIndex),
                new StampedLockKey(objectHashCode));
    }
}
