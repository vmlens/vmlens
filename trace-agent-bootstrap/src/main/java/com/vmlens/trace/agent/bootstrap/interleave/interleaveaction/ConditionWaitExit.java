package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;


import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.WaitExitOperation;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

/**
 * similar as monitor enter only that it does not take part in deadlocks
 */

public class ConditionWaitExit implements InterleaveAction {

    private final int threadIndex;
    private final LockKey lockOrMonitor;

    public ConditionWaitExit(int threadIndex, LockKey lockOrMonitor) {
        this.threadIndex = threadIndex;
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
        return threadIndex;
    }

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other) {
        if(! (other instanceof ConditionWaitExit)) {
            return false;
        }
        ConditionWaitExit otherLock = (ConditionWaitExit) other;
        if(threadIndex != otherLock.threadIndex)  {
            return false;
        }

        return lockOrMonitor.equalsNormalized(normalizeContext,otherLock.lockOrMonitor);
    }
}
