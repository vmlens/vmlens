package com.vmlens.trace.agent.bootstrap.interleave.lockcontainer;

import com.vmlens.trace.agent.bootstrap.interleave.activelock.LockStartOperation;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.LockContainer;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.LockContainerVisitor;

public class Block implements LockContainer, LockContainerVisitor {

    private final LockStartOperation start;
    private final BlockEnd end;

    public Block(LockStartOperation start, BlockEnd end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public AddToAlternatingOrder accept(LockContainerVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public TreeBuilderNode addToAlternatingOrder(LockContainer lockOrConditionContainer,
                                                 BuildAlternatingOrderContext context,
                                                 TreeBuilderNode treeBuilderNode) {
        AddToAlternatingOrder tuple = lockOrConditionContainer.accept(this);
        return tuple.addToAlternatingOrder(context,treeBuilderNode);
    }

    @Override
    public int threadIndex() {
        return start.position().threadIndex();
    }

    public LockStartOperation start() {
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
