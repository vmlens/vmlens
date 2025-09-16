package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.LockEnterOperation;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public class LockEnterImpl implements InterleaveAction  {

    private final int threadIndex;
    private final LockKey lockOrMonitor;

    public LockEnterImpl(int threadIndex, LockKey lockOrMonitor) {
        this.threadIndex = threadIndex;
        this.lockOrMonitor = lockOrMonitor;
    }
    
    @Override
    public void addToKeyToOperationCollection(Position myPosition,
                                              ActiveLockCollection mapContainingStack,
                                              KeyToOperationCollection result) {
        mapContainingStack.push(new LockEnterOperation(myPosition, lockOrMonitor));
    }
    
    @Override
    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LockEnterImpl that = (LockEnterImpl) o;

        return lockOrMonitor.equals(that.lockOrMonitor);
    }

    @Override
    public int hashCode() {
        return lockOrMonitor.hashCode();
    }
    
    @Override
    public String toString() {
        return "LockEnterImpl{" +
                "threadIndex=" + threadIndex +
                ", lockOrMonitor=" + lockOrMonitor +
                '}';
    }

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other) {
        if(! (other instanceof LockEnterImpl)) {
            return false;
        }
        LockEnterImpl otherLock = (LockEnterImpl) other;
        if(threadIndex != otherLock.threadIndex)  {
            return false;
        }
        return lockOrMonitor.equalsNormalized(normalizeContext,otherLock.lockOrMonitor);
    }

    @Override
    public boolean startsThread() {
        return false;
    }
}
