package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

import static com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.LockExit.processLockExit;

/**
 * when we enter a wait we need to release the lock so this
 * behaves as lock exit
 *
 */

public class ConditionWaitEnter implements InterleaveAction  {

    private final MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex;
    private final LockKey lockOrMonitor;

    public ConditionWaitEnter(MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex,
                              LockKey lockOrMonitor) {
        this.methodIdByteCodePositionAndThreadIndex = methodIdByteCodePositionAndThreadIndex;
        this.lockOrMonitor = lockOrMonitor;
    }

    @Override
    public void addToKeyToOperationCollection(Position myPosition,
                                              ActiveLockCollection mapContainingStack,
                                              KeyToOperationCollection result) {
        processLockExit(lockOrMonitor, myPosition, mapContainingStack, result);


    }

    @Override
    public int threadIndex() {
        return methodIdByteCodePositionAndThreadIndex.threadIndex();
    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        if(! (other instanceof ConditionWaitEnter)) {
            return false;
        }
        ConditionWaitEnter otherLock = (ConditionWaitEnter) other;
        if(! methodIdByteCodePositionAndThreadIndex.equals(otherLock.methodIdByteCodePositionAndThreadIndex))  {
            return false;
        }
        return lockOrMonitor.equalsNormalized(otherLock.lockOrMonitor);
    }

    @Override
    public String toString() {
        return "conditionWaitEnter(" +
                methodIdByteCodePositionAndThreadIndex.threadIndex() +
                ","  + lockOrMonitor +
                ");";
    }

}
