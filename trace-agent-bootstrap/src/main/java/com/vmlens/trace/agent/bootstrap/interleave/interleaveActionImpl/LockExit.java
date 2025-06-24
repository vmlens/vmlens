package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.LockEnterOrTryLock;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Lock;
import com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer.Block;
import com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer.BlockEnd;
import com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer.BlockEndOperation;
import com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer.BlockStart;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public class LockExit implements InterleaveAction, BlockEndOperation {

    private final int threadIndex;
    private final Lock lockOrMonitor;

    public LockExit(int threadIndex, Lock lockOrMonitor) {
        this.threadIndex = threadIndex;
        this.lockOrMonitor = lockOrMonitor;
    }


    @Override
    public void addToKeyToOperationCollection(Position myPosition,
                                              ActiveLockCollection mapContainingStack,
                                              KeyToOperationCollection result) {
        ElementAndPosition<LockEnterOrTryLock> enter = mapContainingStack.pop(threadIndex, lockOrMonitor.key());
        if(enter != null) {
            result.addLockOrCondition(lockOrMonitor.key(),
          new Block(new BlockStart(enter.position(),enter.element()), new BlockEnd(myPosition,this)));
        }
    }



    @Override
    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LockExit that = (LockExit) o;

        return lockOrMonitor.equals(that.lockOrMonitor);
    }

    @Override
    public int hashCode() {
        return lockOrMonitor.hashCode();
    }

    public Lock lockOrMonitor() {
        return lockOrMonitor;
    }

    @Override
    public String toString() {
        return "LockExit{" +
                "threadIndex=" + threadIndex +
                ", lockOrMonitor=" + lockOrMonitor +
                '}';
    }

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other) {
        if(! (other instanceof LockExit)) {
            return false;
        }
        LockExit otherLock = (LockExit) other;
        if(threadIndex != otherLock.threadIndex)  {
            return false;
        }

        return lockOrMonitor.key().equalsNormalized(normalizeContext,otherLock.lockOrMonitor.key());
    }
}
