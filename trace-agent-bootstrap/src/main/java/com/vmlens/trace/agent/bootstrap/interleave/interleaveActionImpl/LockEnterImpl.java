package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.LockEnter;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Lock;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public class LockEnterImpl implements InterleaveAction, LockEnter {

    private final int threadIndex;
    private final Lock lockOrMonitor;

    public LockEnterImpl(int threadIndex, Lock lockOrMonitor) {
        this.threadIndex = threadIndex;
        this.lockOrMonitor = lockOrMonitor;
    }

    @Override
    public void addToBlockingLockRelationBuilder(Position position, BlockingLockRelationBuilder builder) {
        ElementAndPosition<LockEnter> element = new ElementAndPosition<>(this, position);
        builder.onLockEnter(element);
    }

    @Override
    public LockKey key() {
        return lockOrMonitor.key();
    }


    @Override
    public void addToKeyToOperationCollection(Position myPosition, ActiveLockCollection mapContainingStack, KeyToOperationCollection result) {
        // Fixme
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

    public Lock lockOrMonitor() {
        return lockOrMonitor;
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

        return lockOrMonitor.key().equalsNormalized(normalizeContext,otherLock.key());
    }
}
