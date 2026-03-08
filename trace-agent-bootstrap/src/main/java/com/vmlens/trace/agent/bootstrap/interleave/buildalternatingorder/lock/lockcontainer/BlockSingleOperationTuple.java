package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ListBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;

public class BlockSingleOperationTuple implements AddToAlternatingOrder  {

    private final Block block;
    private final SingleOperation singleOperation;

    public BlockSingleOperationTuple(Block block, SingleOperation singleOperation) {
        this.block = block;
        this.singleOperation = singleOperation;
    }

    /*
     *  enter, exit
     *  read state
     *
     */
    @Override
    public ListBuilderNode addToAlternatingOrder(BuildAlternatingOrderContext context,
                                                 ListBuilderNode listBuilderNode) {
        ListBuilderNode current =  buildEither(listBuilderNode,block.start().position());
        return  buildEither(current,block.end().position());

    }

    public ListBuilderNode buildEither(ListBuilderNode listBuilderNode, Position blockPosition) {
        return listBuilderNode.either(new AlternativeOneOrder(lbr(singleOperation.position(), blockPosition)),
                new AlternativeOneOrder( lbr(blockPosition, singleOperation.position())));
    }

}
