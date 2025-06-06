package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;

public class StartOrEither {

    public Choice choice() {
        return new Choice();
    }


    public Either either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        return new Either(orderAlternativeA,orderAlternativeB);
    }

}
