package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderListElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.CreateOrderContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

/**
 * to make the OrderTree testable
 * and to separate the permutation logic and OrderTree from the calculation of the
 * run
 *
 */

public class OrderArrayListFactory {


    private final OrderArrayList orderArrayList;

    public OrderArrayListFactory(LeftBeforeRight[] fixedOrderArray) {
        this.orderArrayList = new OrderArrayList(fixedOrderArray);
    }

    /**
     * can return null
     */
    public OrderArrayList create(OrderList orderList, int[] current, CycleFoundCallback cycleFoundCallback) {
        orderArrayList.reset();
        CreateOrderContext createOrderContext = new CreateOrderContext(orderArrayList);
        int position = 0;
        for(TLinkableWrapper<OrderListElement>  element : orderList) {
            element.element().addOrder(createOrderContext,current[position]);
            position++;
        }
        return orderArrayList;
    }

}
