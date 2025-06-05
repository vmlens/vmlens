package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeTuple;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;

public abstract class AbstractNodeBuilder implements NodeBuilder {

    protected NodeBuilder next;

    public SingleChildNodeBuilder listNode(OrderAlternative firstAlternative,
                                           OrderAlternative secondAlternative) {
        SingleChildNodeBuilder builder = new SingleChildNodeBuilder(firstAlternative, secondAlternative);
        this.next =  builder;
        return builder;
    }

    public TwoChildrenNodeBuilder either(AlternativeTuple first, AlternativeTuple second )  {
        TwoChildrenNodeBuilder builder = new TwoChildrenNodeBuilder(new SingleChildNodeBuilder(first),
                new SingleChildNodeBuilder(second));
        this.next =  builder;
        return builder;
    }

}
