package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ListBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.AlternativeMultipleOrders.alternativeTwoOrders;

public class NotifyWaitTuple implements AddToAlternatingOrder {

    private final BarrierOperationAndPosition<BarrierNotify> notify;
    private final BarrierOperationAndPosition<BarrierWaitEnter> wait;

    public NotifyWaitTuple(BarrierOperationAndPosition<BarrierNotify> notify,
                           BarrierOperationAndPosition<BarrierWaitEnter> wait) {
        this.notify = notify;
        this.wait = wait;
    }

    @Override
    public ListBuilderNode addToAlternatingOrder(BuildAlternatingOrderContext context, ListBuilderNode listBuilderNode) {
        AlternativeOneOrder alternativeOneOrder = new AlternativeOneOrder(lbr(notify.position(),wait.position()));

        /*
         * if we want to have timeouts we need a choice, and make sure that notify comes after the next wait
         */
        OrderAlternative second = alternativeTwoOrders(lbr(wait.position(),notify.position()),
                    lbr(notify.position(),wait.position().increment()));

        return listBuilderNode.either(alternativeOneOrder,second);
    }
}
