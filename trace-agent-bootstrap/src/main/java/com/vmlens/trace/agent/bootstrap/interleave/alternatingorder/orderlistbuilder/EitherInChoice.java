package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderAlternative;

public interface EitherInChoice {

    EitherInChoice either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB);

}
