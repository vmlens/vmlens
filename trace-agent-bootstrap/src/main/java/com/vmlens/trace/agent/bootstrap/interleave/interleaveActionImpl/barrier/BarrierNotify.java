package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.BarrierOperationVisitor;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.DependentOperationTuple;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;

public class BarrierNotify implements Barrier , BarrierOperationVisitor{

    private final BarrierKey barrierKey;

    public BarrierNotify(BarrierKey barrierKey) {
        this.barrierKey = barrierKey;
    }


    @Override
    public void addToAlternatingOrder(Position myPosition,
                                      DependentOperationAndPosition<Barrier> other,
                                      BuildAlternatingOrderContext context) {
        DependentOperationTuple tuple = other.element().accept(this, myPosition, other.position());
        if(tuple != null) {
            tuple.addToAlternatingOrder(context);
        }
    }

    @Override
    public DependentOperationTuple accept(BarrierOperationVisitor visitor, Position visitorPosition, Position acceptingPosition) {
        return visitor.visit(visitorPosition,this,acceptingPosition);
    }

    @Override
    public DependentOperationTuple visit(Position myPosition, BarrierNotify other, Position otherPosition) {
        // if both are notify nothing to do
        return null;
    }

    @Override
    public DependentOperationTuple visit(Position myPosition, BarrierWait other, Position otherPosition) {
        return new NotifyWaitTuple(new DependentOperationAndPosition<>(myPosition,this),
                new DependentOperationAndPosition<>(otherPosition,other));
    }
}
