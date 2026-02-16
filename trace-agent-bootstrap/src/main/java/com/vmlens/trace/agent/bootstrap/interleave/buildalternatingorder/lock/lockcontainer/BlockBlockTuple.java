package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.EitherInChoiceAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ListBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;

public class BlockBlockTuple implements AddToAlternatingOrder {

    private final Block first;
    private final Block second;

    public BlockBlockTuple(Block first, Block second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ListBuilderNode addToAlternatingOrder(BuildAlternatingOrderContext context, ListBuilderNode listBuilderNode) {
        /* enter and exist work on the same type of lock
         * so it is only necessary to check the enter of the lock
         * should also work for lock conversion
         */
        if(! first.start().isRead() || !  second.start().isRead()) {
            if(!context.isInDeadlock(this)) {
                return listBuilderNode.either(new AlternativeOneOrder(lbr(first.end().position(),second.start().position())),
                        new AlternativeOneOrder(lbr(second.end().position(),first.start().position())) );
            }
        }
        return listBuilderNode;
    }

    public Block first() {
        return first;
    }

    public Block second() {
        return second;
    }

    public EitherInChoiceAlternative addWhenInDeadlock(EitherInChoiceAlternative treeBuilderNode ) {
        return treeBuilderNode.either(new AlternativeOneOrder(lbr(first.end().position(),second.start().position())),
                new AlternativeOneOrder(lbr(second.end().position(),first.start().position())) );
    }
}
