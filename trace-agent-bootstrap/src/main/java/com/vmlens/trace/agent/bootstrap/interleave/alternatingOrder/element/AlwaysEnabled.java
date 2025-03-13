package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.element;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CreateOrderContext;

public class AlwaysEnabled implements AlternatingOrderElementStrategy {
    @Override
    public boolean isEnabled(CreateOrderContext createOrderContext) {
        return true;
    }
}
