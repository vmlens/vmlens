package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;

public class ChoiceAlternative  implements EitherInChoice {

    private EitherInChoiceAlternative next;

    public EitherInChoice either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        EitherInChoiceAlternative temp = new EitherInChoiceAlternative(orderAlternativeA,orderAlternativeB);
        next = temp;
        return temp;
    }

    public EitherInChoiceAlternative next() {
        return next;
    }

    public OrderTreeNode build() {
        return next.build();
    }
}
