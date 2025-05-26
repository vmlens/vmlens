package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

public class AlternativeOneOrder implements ListNodeAlternative {

    private final LeftBeforeRight leftBeforeRight;

    public AlternativeOneOrder(LeftBeforeRight leftBeforeRight) {
        this.leftBeforeRight = leftBeforeRight;
    }

    @Override
    public boolean process(CreateOrderContext context) {
        context.addOrder(leftBeforeRight);
        return true;
    }
}
