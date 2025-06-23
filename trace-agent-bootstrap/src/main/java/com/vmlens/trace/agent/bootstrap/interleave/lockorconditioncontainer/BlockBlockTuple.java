package com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.AddToAlternatingOrder;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;

public class BlockBlockTuple implements AddToAlternatingOrder  {

    private final Block first;
    private final Block second;

    public BlockBlockTuple(Block first, Block second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public TreeBuilderNode addToAlternatingOrder(BuildAlternatingOrderContext context, TreeBuilderNode treeBuilderNode) {
        return treeBuilderNode.either(new AlternativeOneOrder(lbr(first.end().position(),second.start().position())),
                new AlternativeOneOrder(lbr(second.end().position(),first.start().position())) );
    }
}
