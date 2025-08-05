package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;

public interface TreeBuilderNode  {

    Choice choice();
    Either either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB);

}
