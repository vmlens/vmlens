package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.element.AlternatingOrderElementStrategy;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.element.OnlyWhenNotDeadlockActive;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlockElement;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.LockEnter;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Lock;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class LockEnterImpl implements InterleaveAction, LockEnter {

    private final int threadIndex;
    private final Lock lockOrMonitor;

    public LockEnterImpl(int threadIndex, Lock lockOrMonitor) {
        this.threadIndex = threadIndex;
        this.lockOrMonitor = lockOrMonitor;
    }

    @Override
    public AlternatingOrderElementStrategy alternatingOrderElementStrategy() {
        return new OnlyWhenNotDeadlockActive(lockOrMonitor.key());
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
    public void blockBuilderAdd(Position myPosition,
                                ActiveLockCollection mapContainingStack,
                                MapOfBlocks result) {
        mapContainingStack.push(new ElementAndPosition<>(this, myPosition));
    }

    @Override
    public boolean startsAlternatingOrder(DependentBlockElement interleaveAction) {
        LockExit other = (LockExit) interleaveAction;
        return lockOrMonitor.startsAlternatingOrder(other.lockOrMonitor());
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
}
