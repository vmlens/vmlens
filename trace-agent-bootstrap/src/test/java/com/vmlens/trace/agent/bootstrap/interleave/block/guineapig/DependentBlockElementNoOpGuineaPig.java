package com.vmlens.trace.agent.bootstrap.interleave.block.guineapig;

import com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlockElement;

public class DependentBlockElementNoOpGuineaPig implements DependentBlockElement {

    @Override
    public boolean startsAlternatingOrder(DependentBlockElement interleaveAction) {
        return false;
    }

}
