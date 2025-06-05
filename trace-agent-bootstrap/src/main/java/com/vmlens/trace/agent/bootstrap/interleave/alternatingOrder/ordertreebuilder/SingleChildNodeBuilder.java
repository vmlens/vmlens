package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeTuple;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.SingleChildNode;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;

public class SingleChildNodeBuilder extends AbstractNodeBuilder {

    private final OrderAlternative firstAlternative;
    private final OrderAlternative secondAlternative;


    public SingleChildNodeBuilder(AlternativeTuple eitherAlternatives) {
        this.firstAlternative = eitherAlternatives.first();
        this.secondAlternative = eitherAlternatives.second();
    }

    public SingleChildNodeBuilder(OrderAlternative firstAlternative,
                                  OrderAlternative secondAlternative) {
        this.firstAlternative = firstAlternative;
        this.secondAlternative = secondAlternative;
    }


    @Override
    public OrderTreeNode build() {
        OrderTreeNode nextNode = null;
        if(next != null) {
            nextNode = next.build();
        }

        return new SingleChildNode(nextNode, firstAlternative, secondAlternative);
    }
}
