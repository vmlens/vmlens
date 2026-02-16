package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;

public interface OrderListNode {

    OrderListNode nextAndAddToOrder(CreateOrderContext context, boolean firstAlternative);

    OrderListNode nextLeft();

    boolean hasSameOrder(OrderListNode other);

}
