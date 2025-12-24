package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.ListElementChoiceAlternative;

public interface ChoiceElement {

    EitherInChoiceAlternative getNext();
    void fill();
    ListElementChoiceAlternative build();


}
