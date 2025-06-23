package com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.AddToAlternatingOrder;

public class BlockBlockTuple implements AddToAlternatingOrder  {

    private final Block first;
    private final Block second;

    public BlockBlockTuple(Block first, Block second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public TreeBuilderNode addToAlternatingOrder(BuildAlternatingOrderContext context, TreeBuilderNode treeBuilderNode) {
        first.end().createTuple(second.start());


        return null;
    }
}
