package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.CreateOrderContext;

public interface AlternatingOrderElementStrategy {

    boolean isEnabled(CreateOrderContext createOrderContext);

}
