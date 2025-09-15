package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.BarrierOperationVisitor;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

import java.util.Objects;

public class BarrierWaitEnter implements Barrier, BarrierOperationVisitor {

    private final int threadIndex;
    private final BarrierKey barrierKey;

    public BarrierWaitEnter(int threadIndex,
                            BarrierKey barrierKey) {
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
        DependentOperationAndPosition<Barrier> other = (DependentOperationAndPosition<Barrier>) otherObj;
        AddToAlternatingOrder tuple = other.element().accept(this, myPosition, other.position());
        if(tuple != null) {
            return tuple.addToAlternatingOrder(context,treeBuilderNode);
        }
        return treeBuilderNode;
    }

    @Override
    public AddToAlternatingOrder accept(BarrierOperationVisitor visitor, Position visitorPosition, Position acceptingPosition) {
        return visitor.visit(visitorPosition,this,acceptingPosition);
    }

    @Override
    public AddToAlternatingOrder visit(Position myPosition, BarrierNotify other, Position otherPosition) {
        return new NotifyWaitTuple(new BarrierOperationAndPosition<>(otherPosition,other),
                new BarrierOperationAndPosition<>(myPosition,this));
    }

    @Override
    public AddToAlternatingOrder visit(Position myPosition, BarrierWaitEnter other, Position otherPosition) {
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        BarrierWaitEnter that = (BarrierWaitEnter) object;
        return Objects.equals(barrierKey, that.barrierKey);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(barrierKey);
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other) {
        if(! (other instanceof BarrierWaitEnter)) {
            return false;
        }
        BarrierWaitEnter otherLock = (BarrierWaitEnter) other;
        if(threadIndex != otherLock.threadIndex)  {
            return false;
        }
        return barrierKey.equalsNormalized(normalizeContext,otherLock.barrierKey);
    }

    @Override
    public void addToKeyToOperationCollection(Position myPosition,
                                              ActiveLockCollection mapContainingStack,
                                              KeyToOperationCollection result) {
        result.addBarrier(barrierKey,new DependentOperationAndPosition<>(myPosition,this));
    }

    @Override
    public boolean startsThread() {
        return false;
    }
}
