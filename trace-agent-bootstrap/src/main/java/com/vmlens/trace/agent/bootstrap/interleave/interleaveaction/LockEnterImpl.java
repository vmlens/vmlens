package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.LockEnterOperation;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

import java.util.Objects;

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
    public String toString() {
        return "lockEnter(" +
                 methodIdByteCodePositionAndThreadIndex.threadIndex() +
                ","  + lockOrMonitor +
                ");";
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

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        LockEnterImpl lockEnter = (LockEnterImpl) object;
        return Objects.equals(methodIdByteCodePositionAndThreadIndex, lockEnter.methodIdByteCodePositionAndThreadIndex) && Objects.equals(lockOrMonitor, lockEnter.lockOrMonitor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(),methodIdByteCodePositionAndThreadIndex, lockOrMonitor);
    }
}
