package com.vmlens.trace.agent.bootstrap.interleave.block.dependent;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element.AlternatingOrderElementStrategy;

public interface DependentBlockElement {

    boolean startsAlternatingOrder(DependentBlockElement interleaveAction);
    AlternatingOrderElementStrategy alternatingOrderElementStrategy();

}
