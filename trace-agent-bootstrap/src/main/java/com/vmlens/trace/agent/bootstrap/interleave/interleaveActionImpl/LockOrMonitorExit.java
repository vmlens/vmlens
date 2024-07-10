package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlockElement;
import com.vmlens.trace.agent.bootstrap.interleave.block.LockOrMonitorEnter;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.LockOrMonitor;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class LockOrMonitorExit implements InterleaveAction, DependentBlockElement {

    private final LockOrMonitor lockOrMonitor;

    public LockOrMonitorExit(LockOrMonitor lockOrMonitor) {
        this.lockOrMonitor = lockOrMonitor;
    }

    public static LockOrMonitorExit exit(LockOrMonitor lockOrMonitor) {
        return new LockOrMonitorExit(lockOrMonitor);
    }

    @Override
    public Object blockBuilderKey() {
        return lockOrMonitor.key();
    }

    @Override
    public void blockBuilderAdd(Position myPosition, MapOfBlocks result) {
        ElementAndPosition<LockOrMonitorEnter> enter = result.pop(lockOrMonitor.key());
        DependentBlock dependentBlock = new DependentBlock((ElementAndPosition) enter,
                new ElementAndPosition<DependentBlockElement>(this, myPosition));
        result.addDependent(lockOrMonitor.key(), dependentBlock);
    }

    @Override
    public boolean startsAlternatingOrder(DependentBlockElement interleaveAction) {
        LockOrMonitorEnterImpl other = (LockOrMonitorEnterImpl) interleaveAction;
        return lockOrMonitor.startsAlternatingOrder(other.lockOrMonitor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LockOrMonitorExit that = (LockOrMonitorExit) o;

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
