package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderList;

public interface AlternatingOrderContainerForCycleFound {
    OrderList orderList();
    void resetOrderList(OrderList orderList);
}
