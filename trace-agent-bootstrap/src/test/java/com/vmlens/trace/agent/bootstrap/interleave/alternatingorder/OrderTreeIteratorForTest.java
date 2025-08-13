package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.CreateOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeIterator;

public class OrderTreeIteratorForTest implements OrderTreeIterator  {

    private final LeftBeforeRight[] order;
    private int index;

    public OrderTreeIteratorForTest(LeftBeforeRight[] order) {
        this.order = order;
    }

    @Override
    public boolean hasNext() {
        return index < order.length;
    }

    @Override
    public boolean advanceAndAddToOrder(CreateOrderContext context, boolean firstAlternative) {
        context.addOrder(order[index]);
        index++;
        return true;
    }
}
