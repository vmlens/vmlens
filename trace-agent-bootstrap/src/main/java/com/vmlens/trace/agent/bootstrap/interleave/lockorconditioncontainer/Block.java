package com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.LockOrConditionContainer;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.LockOrConditionContainerVisitor;

public class Block implements LockOrConditionContainer, LockOrConditionContainerVisitor {

    private final BlockStart start;
    private final BlockEnd end;

    public Block(BlockStart start, BlockEnd end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public AddToAlternatingOrder accept(LockOrConditionContainerVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public TreeBuilderNode addToAlternatingOrder(LockOrConditionContainer lockOrConditionContainer,
                                                 BuildAlternatingOrderContext context,
                                                 TreeBuilderNode treeBuilderNode) {
        return null;
    }

    @Override
    public int threadIndex() {
        return 0;
    }

    public BlockStart start() {
        return start;
    }

    public BlockEnd end() {
        return end;
    }

    @Override
    public AddToAlternatingOrder visit(Block block) {
        return new BlockBlockTuple(this,block);
    }
}
