package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.dependentoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.BarrierOperationVisitor;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;

public class BarrierNotify implements Barrier , BarrierOperationVisitor {

    private final BarrierKey barrierKey;

    public BarrierNotify(BarrierKey barrierKey) {
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
    public AddToAlternatingOrder visit(Position myPosition, BarrierWait other, Position otherPosition) {
        return new NotifyWaitTuple(new BarrierOperationAndPosition<>(myPosition,this),
                new BarrierOperationAndPosition<>(otherPosition,other));
    }
}
