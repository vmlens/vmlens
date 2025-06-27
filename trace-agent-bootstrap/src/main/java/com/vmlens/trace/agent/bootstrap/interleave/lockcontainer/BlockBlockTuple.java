package com.vmlens.trace.agent.bootstrap.interleave.lockcontainer;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.EitherInChoice;
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
        /* enter and exist work on the same type of lock
         * so it is only necessary to check the enter of the lock
         * should also work for lock conversion
         */
        if(! first.start().isReadLock() || !  second.start().isReadLock()) {
            if(!context.isInDeadlock(this)) {
                return treeBuilderNode.either(new AlternativeOneOrder(lbr(first.end().position(),second.start().position())),
                        new AlternativeOneOrder(lbr(second.end().position(),first.start().position())) );
            }
        }
        return treeBuilderNode;
    }

    public Block first() {
        return first;
    }

    public Block second() {
        return second;
    }

    public EitherInChoice addWhenInDeadlock(EitherInChoice treeBuilderNode ) {
        return treeBuilderNode.either(new AlternativeOneOrder(lbr(first.end().position(),second.start().position())),
                new AlternativeOneOrder(lbr(second.end().position(),first.start().position())) );
    }
}
