package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.dependentoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.BarrierOperationVisitor;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.AddToAlternatingOrder;

public class BarrierWait implements Barrier, BarrierOperationVisitor {

    private final BarrierKey barrierKey;

    public BarrierWait(BarrierKey barrierKey) {
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
    public AddToAlternatingOrder visit(Position myPosition, BarrierWait other, Position otherPosition) {
        return null;
    }
}
