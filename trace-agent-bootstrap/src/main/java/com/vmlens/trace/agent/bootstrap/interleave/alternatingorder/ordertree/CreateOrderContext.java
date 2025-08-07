package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;

public class CreateOrderContext {

    private final OrderArrayList orderArrayList;

    public CreateOrderContext(OrderArrayList orderArrayList) {
        this.orderArrayList = orderArrayList;
    }

    public void addOrder(LeftBeforeRight order) {
        orderArrayList.add(order);
    }
}
