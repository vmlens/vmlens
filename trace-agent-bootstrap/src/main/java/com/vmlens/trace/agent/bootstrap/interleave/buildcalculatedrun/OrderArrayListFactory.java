package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.Permutation;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.PermutationIterator;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.CreateOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeIterator;

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
    public OrderArrayList create(OrderTreeIterator orderTreeIterator,
                                 PermutationIterator permutationIterator) {
        orderArrayList.reset();
        Permutation current =  permutationIterator.next();

        CreateOrderContext createOrderContext = new CreateOrderContext(orderArrayList);
        OrderTreeIterator iter = orderTreeIterator;
        int position = 0;
        while(iter.hasNext()) {
            if(! iter.advanceAndAddToOrder(createOrderContext,current.at(position))) {
                return null;
            }
            position++;

        }
        return orderArrayList;
    }

}
