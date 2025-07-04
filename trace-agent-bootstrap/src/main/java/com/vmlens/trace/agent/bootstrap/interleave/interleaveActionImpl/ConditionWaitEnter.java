package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

import static com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.LockExit.processLockExit;

/**
 * when we enter a wait we need to release the lock so this
 * behaves as lock exit
 *
 */

public class ConditionWaitEnter implements InterleaveAction  {

    private final int threadIndex;
    private final LockKey lockOrMonitor;

    public ConditionWaitEnter(int threadIndex, LockKey lockOrMonitor) {
        this.threadIndex = threadIndex;
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
        return threadIndex;
    }

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other) {
        if(! (other instanceof ConditionWaitEnter)) {
            return false;
        }
        ConditionWaitEnter otherLock = (ConditionWaitEnter) other;
        if(threadIndex != otherLock.threadIndex)  {
            return false;
        }
        return lockOrMonitor.equalsNormalized(normalizeContext,otherLock.lockOrMonitor);
    }
}
