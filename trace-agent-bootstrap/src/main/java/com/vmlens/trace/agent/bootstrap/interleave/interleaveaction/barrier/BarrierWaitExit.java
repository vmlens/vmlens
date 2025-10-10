package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.BarrierOperationVisitor;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.MethodIdByteCodePositionAndThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;

import java.util.Objects;

public class BarrierWaitExit implements Barrier  {

    private final MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex;
    private final BarrierKey barrierKey;

    public BarrierWaitExit(MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex,
                           BarrierKey barrierKey) {
        this.methodIdByteCodePositionAndThreadIndex = methodIdByteCodePositionAndThreadIndex;
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
        return methodIdByteCodePositionAndThreadIndex.threadIndex();
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
    public boolean equalsNormalized(InterleaveAction other) {
        if(! (other instanceof BarrierWaitExit)) {
            return false;
        }
        BarrierWaitExit otherLock = (BarrierWaitExit) other;
        if(! methodIdByteCodePositionAndThreadIndex.equals(otherLock.methodIdByteCodePositionAndThreadIndex))  {
            return false;
        }
        return barrierKey.equalsNormalized(otherLock.barrierKey);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        BarrierWaitExit that = (BarrierWaitExit) object;
        return Objects.equals(methodIdByteCodePositionAndThreadIndex, that.methodIdByteCodePositionAndThreadIndex) && Objects.equals(barrierKey, that.barrierKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(),methodIdByteCodePositionAndThreadIndex, barrierKey);
    }
}
