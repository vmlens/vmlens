package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.element;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CreateOrderContext;

public interface AlternatingOrderElementStrategy {

    boolean isEnabled(CreateOrderContext createOrderContext);

}
