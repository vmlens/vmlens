package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;

public abstract class StartOrEither implements NodeBuilder {

    private NodeBuilder next;

    public Choice choice() {
        Choice temp = new Choice();
        next = temp;
        return temp;
    }


    public Either either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        Either temp = new Either(orderAlternativeA,orderAlternativeB);
        next = temp;
        return temp;
    }

    public NodeBuilder getNext() {
        return next;
    }
}
