package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.CreateOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeIterator;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;

public class CalculatedRunFactory {

    private final OrderArrayList orderArrayList;
    private final ElementsPerThreadList<Position> actualRun;

    public CalculatedRunFactory(LeftBeforeRight[] fixedOrderArray,
                                ThreadIndexToElementList<Position> actualRun) {
        this.orderArrayList = new OrderArrayList(fixedOrderArray);
        this.actualRun = actualRun.create(Position.class);
    }

    public CalculatedRun create(OrderTreeIterator orderTreeIterator,
                                       PermutationIterator permutationIterator) {
        // reset the cached list
        orderArrayList.reset();
        actualRun.reset();

        CreateOrderContext createOrderContext = new CreateOrderContext(orderArrayList);
        OrderTreeIterator iter = orderTreeIterator;
        int position = 0;
        while(iter.hasNext()) {
            iter.advanceAndAddToOrder(createOrderContext,permutationIterator.at(position));
            position++;
        }
        permutationIterator.advance();

        return new CreateCalculatedRun(orderArrayList, actualRun).create();
    }

}
