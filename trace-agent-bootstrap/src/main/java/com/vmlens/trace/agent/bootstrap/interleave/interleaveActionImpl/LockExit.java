package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.LockEnter;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element.AlternatingOrderElementStrategy;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element.OnlyWhenNotDeadlockActive;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlockElement;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Lock;
import com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer.BlockEndOperation;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public class LockExit implements InterleaveAction, DependentBlockElement, BlockEndOperation {

    private final int threadIndex;
    private final Lock lockOrMonitor;

    public LockExit(int threadIndex, Lock lockOrMonitor) {
        this.threadIndex = threadIndex;
        this.lockOrMonitor = lockOrMonitor;
    }

    @Override
    public AlternatingOrderElementStrategy alternatingOrderElementStrategy() {
        return new OnlyWhenNotDeadlockActive(lockOrMonitor.key());
    }

    @Override
    public void addToBlockingLockRelationBuilder(Position position, BlockingLockRelationBuilder builder) {
        builder.onLockExit(position.threadIndex,lockOrMonitor.key());
    }

    @Override
    public void blockBuilderAdd(Position myPosition,
                                ActiveLockCollection mapContainingStack,
                                MapOfBlocks result) {
        ElementAndPosition<LockEnter> enter = mapContainingStack.pop(myPosition.threadIndex,lockOrMonitor.key());
        if(enter != null) {
            DependentBlock dependentBlock = new DependentBlock((ElementAndPosition) enter,
                    new ElementAndPosition<>(this, myPosition));
            result.addDependent(lockOrMonitor.key(), dependentBlock);
        }
    }

    @Override
    public boolean startsAlternatingOrder(DependentBlockElement interleaveAction) {
        LockEnterImpl other = (LockEnterImpl) interleaveAction;
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
