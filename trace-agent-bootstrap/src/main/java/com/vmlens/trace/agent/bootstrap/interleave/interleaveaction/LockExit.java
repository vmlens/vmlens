package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.LockStartOperation;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer.Block;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer.BlockEnd;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public class LockExit implements InterleaveAction  {

    private final int threadIndex;
    private final LockKey lockOrMonitor;

    public LockExit(int threadIndex, LockKey lockOrMonitor) {
        this.threadIndex = threadIndex;
        this.lockOrMonitor = lockOrMonitor;
    }

    static void processLockExit(LockKey lockOrMonitor,
                                Position myPosition,
                                ActiveLockCollection mapContainingStack,
                                KeyToOperationCollection result) {
        LockStartOperation enter = mapContainingStack.pop(myPosition.threadIndex(), lockOrMonitor);
        if(enter != null) {
            result.addLockOrCondition(lockOrMonitor,
                    new Block(enter, new BlockEnd(myPosition)));
        }
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

        return lockOrMonitor.equalsNormalized(normalizeContext,otherLock.lockOrMonitor);
    }
}
