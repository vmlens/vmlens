package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ListBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;

/**
 * Read State creates an alternating order with all other
 * barrier operations
 * Read State does not generate an alternating order with another read state
 *
 */
public class ReadStateChangeStateTuple implements AddToAlternatingOrder {

    private final BarrierOperationAndPosition<BarrierReadState> readState;
    private final Position changeStatePosition;


    public ReadStateChangeStateTuple(BarrierOperationAndPosition<BarrierReadState> readState,
                                     Position changeStatePosition) {
        this.readState = readState;
        this.changeStatePosition = changeStatePosition;
    }

    @Override
    public ListBuilderNode addToAlternatingOrder(BuildAlternatingOrderContext context, ListBuilderNode listBuilderNode) {
        AlternativeOneOrder alternativeOneOrder = new AlternativeOneOrder(lbr(readState.position(),changeStatePosition));
        OrderAlternative second =  new AlternativeOneOrder(lbr(changeStatePosition,readState.position()));
        return listBuilderNode.either(alternativeOneOrder,second);
    }
}
