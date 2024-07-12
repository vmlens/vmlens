package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlockElement;
import com.vmlens.trace.agent.bootstrap.interleave.block.LockOrMonitorEnter;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapContainingStack;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.LockOrMonitor;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.LockOrMonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class LockOrMonitorEnterImpl implements InterleaveAction, LockOrMonitorEnter {

    private final LockOrMonitor lockOrMonitor;

    public LockOrMonitorEnterImpl(LockOrMonitor lockOrMonitor) {
        this.lockOrMonitor = lockOrMonitor;
    }

    public static LockOrMonitorEnterImpl enter(LockOrMonitor lockOrMonitor) {
        return new LockOrMonitorEnterImpl(lockOrMonitor);
    }

    @Override
    public LockOrMonitorKey key() {
        return lockOrMonitor.key();
    }

    @Override
    public void blockBuilderAdd(Position myPosition,
                                MapContainingStack mapContainingStack,
                                MapOfBlocks result) {
        mapContainingStack.push(new ElementAndPosition<LockOrMonitorEnter>(this, myPosition));
    }

    @Override
    public boolean startsAlternatingOrder(DependentBlockElement interleaveAction) {
        LockOrMonitorExit other = (LockOrMonitorExit) interleaveAction;
        return lockOrMonitor.startsAlternatingOrder(other.lockOrMonitor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LockOrMonitorEnterImpl that = (LockOrMonitorEnterImpl) o;

        return lockOrMonitor.equals(that.lockOrMonitor);
    }

    @Override
    public int hashCode() {
        return lockOrMonitor.hashCode();
    }

    public LockOrMonitor lockOrMonitor() {
        return lockOrMonitor;
    }
}
