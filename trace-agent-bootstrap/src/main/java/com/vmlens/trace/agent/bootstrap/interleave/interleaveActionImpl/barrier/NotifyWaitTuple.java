package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeTwoOrders;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.dependentoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.AddToAlternatingOrder;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;

public class NotifyWaitTuple implements AddToAlternatingOrder {

    private final BarrierOperationAndPosition<BarrierNotify> notify;
    private final BarrierOperationAndPosition<BarrierWait> wait;

    public NotifyWaitTuple(BarrierOperationAndPosition<BarrierNotify> notify,
                           BarrierOperationAndPosition<BarrierWait> wait) {
        this.notify = notify;
        this.wait = wait;
    }

    @Override
    public TreeBuilderNode addToAlternatingOrder(BuildAlternatingOrderContext context, TreeBuilderNode treeBuilderNode) {
        AlternativeOneOrder alternativeOneOrder = new AlternativeOneOrder(lbr(notify.position(),wait.position()));

        OrderAlternative second;
        if(wait.position().positionInThread() >= context.getLastPositionForThreadIndex(wait.position().threadIndex())) {
            second  = new AlternativeOneOrder(lbr(wait.position(),notify.position()));
        } else {
           second =  new AlternativeTwoOrders(lbr(wait.position(),notify.position()),
                    lbr(notify.position(),wait.position().increment()));
        }
        return treeBuilderNode.either(alternativeOneOrder,second);
    }
}
