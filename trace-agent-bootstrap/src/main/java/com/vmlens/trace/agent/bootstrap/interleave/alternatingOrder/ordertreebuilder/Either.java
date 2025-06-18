package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.SingleChildNode;

public class Either extends StartOrEither {

    private final OrderAlternative orderAlternativeA;
    private final OrderAlternative orderAlternativeB;

    public Either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        this.orderAlternativeA = orderAlternativeA;
        this.orderAlternativeB = orderAlternativeB;
    }

    @Override
    public OrderTreeNode build() {
        OrderTreeNode nextNode = null;
        if(getNext() != null) {
            nextNode = getNext().build();
        }
        return new SingleChildNode(nextNode,orderAlternativeA,orderAlternativeB);
    }
}
