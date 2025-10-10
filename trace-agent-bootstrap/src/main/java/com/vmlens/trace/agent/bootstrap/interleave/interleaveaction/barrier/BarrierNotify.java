package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.BarrierOperationVisitor;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.MethodIdByteCodePositionAndThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;

import java.util.Objects;

public class BarrierNotify implements Barrier , BarrierOperationVisitor {

    private final MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex;
    private final BarrierKey barrierKey;

    public BarrierNotify(MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex,
                         BarrierKey barrierKey) {
        this.methodIdByteCodePositionAndThreadIndex = methodIdByteCodePositionAndThreadIndex;
        this.barrierKey = barrierKey;
    }

    @Override
    public TreeBuilderNode addToAlternatingOrder(Position myPosition,
                                                     Object otherObj,
                                                     BuildAlternatingOrderContext context,
                                                     TreeBuilderNode treeBuilderNode) {
        DependentOperationAndPosition<Barrier> other = (DependentOperationAndPosition<Barrier> ) otherObj;
        AddToAlternatingOrder tuple = other.element().accept(this, myPosition, other.position());
        if(tuple != null) {
            return tuple.addToAlternatingOrder(context,treeBuilderNode);
        }
        return treeBuilderNode;
    }

    @Override
    public BarrierKey key() {
        return barrierKey;
    }

    @Override
    public AddToAlternatingOrder accept(BarrierOperationVisitor visitor, Position visitorPosition, Position acceptingPosition) {
        return visitor.visit(visitorPosition,this,acceptingPosition);
    }

    @Override
    public AddToAlternatingOrder visit(Position myPosition, BarrierNotify other, Position otherPosition) {
        // if both are notify nothing to do
        return null;
    }

    @Override
    public AddToAlternatingOrder visit(Position myPosition, BarrierWaitEnter other, Position otherPosition) {
        return new NotifyWaitTuple(new BarrierOperationAndPosition<>(myPosition,this),
                new BarrierOperationAndPosition<>(otherPosition,other));
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        BarrierNotify that = (BarrierNotify) object;
        return Objects.equals(barrierKey, that.barrierKey);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(barrierKey);
    }

    @Override
    public int threadIndex() {
        return methodIdByteCodePositionAndThreadIndex.threadIndex();
    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        if(! (other instanceof BarrierNotify)) {
            return false;
        }
        BarrierNotify otherLock = (BarrierNotify) other;
        if(! methodIdByteCodePositionAndThreadIndex.equals(otherLock.methodIdByteCodePositionAndThreadIndex))  {
            return false;
        }
        return barrierKey.equalsNormalized(otherLock.barrierKey);
    }

    @Override
    public int normalizedHashCode() {
        return Objects.hash(getClass(), methodIdByteCodePositionAndThreadIndex, barrierKey.category());
    }

    @Override
    public void addToKeyToOperationCollection(Position myPosition,
                                              ActiveLockCollection mapContainingStack,
                                              KeyToOperationCollection result) {
        result.addBarrier(barrierKey,new DependentOperationAndPosition<>(myPosition,this));

    }

}
