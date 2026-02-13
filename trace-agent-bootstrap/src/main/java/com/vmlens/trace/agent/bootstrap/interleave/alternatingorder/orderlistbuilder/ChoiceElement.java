package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.ListElementChoiceAlternative;

public interface ChoiceElement {

    EitherInChoiceAlternative getNext();
    void fill();
    ListElementChoiceAlternative build();


}
