package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;


import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.WaitExitOperation;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

/**
 * similar as monitor enter only that it does not take part in deadlocks
 */

public class ConditionWaitExit implements InterleaveAction {

    private final MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex;
    private final LockKey lockOrMonitor;

    public ConditionWaitExit(MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex,
                             LockKey lockOrMonitor) {
        this.methodIdByteCodePositionAndThreadIndex = methodIdByteCodePositionAndThreadIndex;
        this.lockOrMonitor = lockOrMonitor;
    }

    @Override
    public void addToKeyToOperationCollection(Position myPosition,
                                              ActiveLockCollection mapContainingStack,
                                              KeyToOperationCollection result) {
        mapContainingStack.push(new WaitExitOperation(myPosition, lockOrMonitor));
    }

    @Override
    public int threadIndex() {
        return methodIdByteCodePositionAndThreadIndex.threadIndex();
    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        if(! (other instanceof ConditionWaitExit)) {
            return false;
        }
        ConditionWaitExit otherLock = (ConditionWaitExit) other;
        if(! methodIdByteCodePositionAndThreadIndex.equals(otherLock.methodIdByteCodePositionAndThreadIndex))  {
            return false;
        }

        return lockOrMonitor.equalsNormalized(otherLock.lockOrMonitor);
    }

}
