package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;

public interface PartOfChoiceTreeBuilderNode {

    Either either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB);

}
