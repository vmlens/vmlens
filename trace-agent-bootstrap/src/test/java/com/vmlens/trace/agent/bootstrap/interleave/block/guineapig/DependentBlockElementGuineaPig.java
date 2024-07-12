package com.vmlens.trace.agent.bootstrap.interleave.block.guineapig;

import com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlockElement;

public class DependentBlockElementGuineaPig implements DependentBlockElement {

    private final boolean startsAlternatingOrder;

    public DependentBlockElementGuineaPig(boolean startsAlternatingOrder) {
        this.startsAlternatingOrder = startsAlternatingOrder;
    }

    public DependentBlockElementGuineaPig() {
        this.startsAlternatingOrder = false;
    }

    @Override
    public boolean startsAlternatingOrder(DependentBlockElement interleaveAction) {
        return startsAlternatingOrder;
    }

}
