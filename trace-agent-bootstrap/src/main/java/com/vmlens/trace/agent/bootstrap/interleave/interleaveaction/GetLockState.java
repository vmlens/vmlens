package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer.SingleOperation;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

import java.util.Objects;

public class GetLockState implements InterleaveAction {

    private final MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex;
    private final LockKey lockOrMonitor;

    public GetLockState(MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex,
                        LockKey lockOrMonitor) {
        this.methodIdByteCodePositionAndThreadIndex = methodIdByteCodePositionAndThreadIndex;
        this.lockOrMonitor = lockOrMonitor;
    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        if(! (other instanceof GetLockState)) {
            return false;
        }
        GetLockState otherLock = (GetLockState) other;
        if(! methodIdByteCodePositionAndThreadIndex.equals(otherLock.methodIdByteCodePositionAndThreadIndex))  {
            return false;
        }
        return lockOrMonitor.equalsNormalized(lockOrMonitor);
    }

    @Override
    public int threadIndex() {
        return methodIdByteCodePositionAndThreadIndex.threadIndex();
    }

    @Override
    public void addToKeyToOperationCollection(Position myPosition,
                                              ActiveLockCollection mapContainingStack,
                                              KeyToOperationCollection result) {
        result.addLockOrCondition(lockOrMonitor, new SingleOperation(myPosition));
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        GetLockState that = (GetLockState) object;
        return Objects.equals(methodIdByteCodePositionAndThreadIndex, that.methodIdByteCodePositionAndThreadIndex)
                && Objects.equals(lockOrMonitor, that.lockOrMonitor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodIdByteCodePositionAndThreadIndex, lockOrMonitor);
    }
}
