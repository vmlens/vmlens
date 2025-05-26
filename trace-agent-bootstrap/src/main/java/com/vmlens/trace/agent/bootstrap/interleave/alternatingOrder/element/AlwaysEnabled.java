package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.CreateOrderContext;

public class AlwaysEnabled implements AlternatingOrderElementStrategy {
    @Override
    public boolean isEnabled(CreateOrderContext createOrderContext) {
        return true;
    }
}
