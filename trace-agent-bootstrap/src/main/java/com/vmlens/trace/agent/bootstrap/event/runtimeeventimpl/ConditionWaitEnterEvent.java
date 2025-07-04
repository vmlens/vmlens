package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.ConditionWaitEnterEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.LockExitOrWaitEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.ConditionWaitEnter;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.MonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class ConditionWaitEnterEvent extends ConditionWaitEnterEventGen implements
        LockExitOrWaitEvent, WithInMethodIdPositionObjectHashCode  {

    private LockKey lockKey;
    private final ReadWriteLockMap readWriteLockMap;

    public ConditionWaitEnterEvent(ReadWriteLockMap readWriteLockMap) {
        this.readWriteLockMap = readWriteLockMap;
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
    public InterleaveAction create(CreateInterleaveActionContext context) {
        return new ConditionWaitEnter(this.threadIndex, lockKey);
    }

    @Override
    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
    }

    /**
     * this is called for lock condition
     */
    @Override
    public void setInMethodIdPositionObjectHashCode(int inMethodId, int position, long objectHashCode) {
        setLockKey(readWriteLockMap.lockKeyForCondition(objectHashCode),inMethodId,position);
    }

    /**
     * this is called for synchronized monitor
     */
    public void setInMethodIdAndPositionForMonitor(int inMethodId, int position, long objectHashCode) {
        setLockKey(new MonitorKey(this.objectHashCode),inMethodId,position);
    }

    private void setLockKey(LockKey lockKey, int inMethodId, int position) {
        this.lockKey = lockKey;
        this.lockKeyCategory = lockKey.category();
        this.objectHashCode = lockKey.objectHashCode();
        this.methodId = inMethodId;
        this.bytecodePosition = position;
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
    public Integer waitingThreadIndex() {
        return threadIndex;
    }


}
