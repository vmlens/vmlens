package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeTwoOrders;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.DependentOperationTuple;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;

public class NotifyWaitTuple implements DependentOperationTuple {

    private final DependentOperationAndPosition<BarrierNotify> notify;
    private final DependentOperationAndPosition<BarrierWait> wait;

    public NotifyWaitTuple(DependentOperationAndPosition<BarrierNotify> notify,
                           DependentOperationAndPosition<BarrierWait> wait) {
        this.notify = notify;
        this.wait = wait;
    }

    @Override
    public void addToAlternatingOrder(BuildAlternatingOrderContext context) {
        AlternativeOneOrder alternativeOneOrder = new AlternativeOneOrder(lbr(notify.position(),wait.position()));

        OrderAlternative second;
        if(wait.position().positionInThread() >= context.getLastPositionForThreadIndex(wait.position().threadIndex())) {
            second  = new AlternativeOneOrder(lbr(wait.position(),notify.position()));
        } else {
           second =  new AlternativeTwoOrders(lbr(wait.position(),notify.position()),
                    lbr(notify.position(),wait.position().increment()));
        }

        context.either(alternativeOneOrder,second);
    }
}
