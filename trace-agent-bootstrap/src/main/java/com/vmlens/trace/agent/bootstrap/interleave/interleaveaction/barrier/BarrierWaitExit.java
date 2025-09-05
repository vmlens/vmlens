package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.interleavetypes.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.interleavetypes.BarrierOperationVisitor;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public class BarrierWaitExit implements Barrier  {

    private final int threadIndex;
    private final BarrierKey barrierKey;

    public BarrierWaitExit(int threadIndex, BarrierKey barrierKey) {
        this.threadIndex = threadIndex;
        this.barrierKey = barrierKey;
    }

    @Override
    public BarrierKey key() {
        return barrierKey;
    }

    @Override
    public TreeBuilderNode addToAlternatingOrder(Position myPosition,
                                                 Object otherObj,
                                                 BuildAlternatingOrderContext context,
                                                 TreeBuilderNode treeBuilderNode) {
        // Barrier exit does not generate order relations
        return treeBuilderNode;
    }


    @Override
    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public void addToKeyToOperationCollection(Position myPosition, ActiveLockCollection mapContainingStack, KeyToOperationCollection result) {
        // Barrier exit does not generate order relations
    }

    @Override
    public AddToAlternatingOrder accept(BarrierOperationVisitor visitor, Position visitorPosition, Position acceptingPosition) {
        return null;
    }

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other) {
        if(! (other instanceof BarrierWaitExit)) {
            return false;
        }
        BarrierWaitExit otherLock = (BarrierWaitExit) other;
        if(threadIndex != otherLock.threadIndex)  {
            return false;
        }
        return barrierKey.equalsNormalized(normalizeContext,otherLock.barrierKey);
    }
}
