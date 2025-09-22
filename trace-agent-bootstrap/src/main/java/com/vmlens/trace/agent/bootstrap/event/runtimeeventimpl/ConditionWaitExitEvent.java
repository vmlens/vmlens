package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.ConditionWaitExitEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.NextStateBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.ConditionWaitExit;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.MonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class ConditionWaitExitEvent extends ConditionWaitExitEventGen implements ExecuteBeforeEvent, WithInMethodIdPositionObjectHashCode   {

    private LockKey lockKey;
    private final ReadWriteLockMap readWriteLockMap;

    public ConditionWaitExitEvent(ReadWriteLockMap readWriteLockMap) {
        this.readWriteLockMap = readWriteLockMap;
    }

    @Override
    public InterleaveAction create(CreateInterleaveActionContext context) {
        return new ConditionWaitExit(this.threadIndex, lockKey);
    }

    @Override
    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
    }

    /**
     * Fixme same as wait enter
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
    public void addToBuilder(NextStateBuilder nextStateBuilder) {
        nextStateBuilder.addExitEvent();
    }

    @Override
    public boolean startsNewThread() {
        return false;
    }
}
