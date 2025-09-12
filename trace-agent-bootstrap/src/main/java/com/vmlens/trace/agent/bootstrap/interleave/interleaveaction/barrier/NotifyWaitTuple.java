package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeTwoOrders;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;

public class NotifyWaitTuple implements AddToAlternatingOrder {

    private final BarrierOperationAndPosition<BarrierNotify> notify;
    private final BarrierOperationAndPosition<BarrierWaitEnter> wait;

    public NotifyWaitTuple(BarrierOperationAndPosition<BarrierNotify> notify,
                           BarrierOperationAndPosition<BarrierWaitEnter> wait) {
        this.notify = notify;
        this.wait = wait;
    }

    @Override
    public TreeBuilderNode addToAlternatingOrder(BuildAlternatingOrderContext context, TreeBuilderNode treeBuilderNode) {
        AlternativeOneOrder alternativeOneOrder = new AlternativeOneOrder(lbr(notify.position(),wait.position()));

        /*
         * if we want to have timeouts we need a choice, and make sure that notify comes after the next wait
         */
        OrderAlternative second =  new AlternativeTwoOrders(lbr(wait.position(),notify.position()),
                    lbr(notify.position(),wait.position().increment()));

        return treeBuilderNode.either(alternativeOneOrder,second);
    }
}
