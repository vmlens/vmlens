package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;

public class Either {

    private final OrderAlternative orderAlternativeA;
    private final OrderAlternative orderAlternativeB;

    public Either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        this.orderAlternativeA = orderAlternativeA;
        this.orderAlternativeB = orderAlternativeB;
    }
}
