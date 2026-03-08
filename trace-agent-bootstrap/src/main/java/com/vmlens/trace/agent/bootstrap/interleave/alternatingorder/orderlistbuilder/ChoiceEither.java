package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderAlternative;

public interface ChoiceEither {

     ChoiceEither either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB);

}
