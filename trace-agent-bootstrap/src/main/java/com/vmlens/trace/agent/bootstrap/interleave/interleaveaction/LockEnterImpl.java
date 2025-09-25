package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.LockEnterOperation;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

public class LockEnterImpl implements InterleaveAction  {

    private final MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex;
    private final LockKey lockOrMonitor;

    public LockEnterImpl(MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex,
                         LockKey lockOrMonitor) {
        this.methodIdByteCodePositionAndThreadIndex = methodIdByteCodePositionAndThreadIndex;
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
        return methodIdByteCodePositionAndThreadIndex.threadIndex();
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
                "threadIndex=" + methodIdByteCodePositionAndThreadIndex.threadIndex() +
                ", lockOrMonitor=" + lockOrMonitor +
                '}';
    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        if(! (other instanceof LockEnterImpl)) {
            return false;
        }
        LockEnterImpl otherLock = (LockEnterImpl) other;
        if(! methodIdByteCodePositionAndThreadIndex.equals(otherLock.methodIdByteCodePositionAndThreadIndex))  {
            return false;
        }

        return lockOrMonitor.equalsNormalized(lockOrMonitor);
    }

}
