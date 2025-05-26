package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

public class AlternativeTwoOrders implements ListNodeAlternative {

    private final LeftBeforeRight firstOrder;
    private final LeftBeforeRight secondOrder;

    public AlternativeTwoOrders(LeftBeforeRight firstOrder, LeftBeforeRight secondOrder) {
        this.firstOrder = firstOrder;
        this.secondOrder = secondOrder;
    }

    @Override
    public boolean process(CreateOrderContext context) {
        context.addOrder(firstOrder);
        context.addOrder(secondOrder);
        return true;
    }

}
