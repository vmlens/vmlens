package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
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
    public TreeBuilderNode addToAlternatingOrder(BuildAlternatingOrderContext context,
                                                 TreeBuilderNode treeBuilderNode) {
        TreeBuilderNode current =  buildEither(treeBuilderNode,block.start().position());
        return  buildEither(current,block.end().position());

    }

    public TreeBuilderNode buildEither(TreeBuilderNode treeBuilderNode, Position blockPosition) {
        return treeBuilderNode.either(new AlternativeOneOrder(lbr(singleOperation.position(), blockPosition)),
                new AlternativeOneOrder( lbr(blockPosition, singleOperation.position())));
    }

}
