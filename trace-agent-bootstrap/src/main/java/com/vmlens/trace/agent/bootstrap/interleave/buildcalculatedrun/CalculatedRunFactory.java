package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.Permutation;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.PermutationIterator;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.CreateOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeIterator;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;

public class CalculatedRunFactory {

    private final CycleDetectionAdapter cycleDetectionAdapter;
    private final OrderArrayList orderArrayList;
    private final InterleaveLoopContext interleaveLoopContext;

    public CalculatedRunFactory(LeftBeforeRight[] fixedOrderArray,
                                ThreadIndexToElementList<Position> actualRun,
                                InterleaveLoopContext interleaveLoopContext) {
        this.orderArrayList = new OrderArrayList(fixedOrderArray);
        this.cycleDetectionAdapter =  new CycleDetectionAdapter(new CalculatedRunBuilder(actualRun));
        this.interleaveLoopContext = interleaveLoopContext;
        ;
    }

    public CalculatedRun create(OrderTreeIterator orderTreeIterator,
                                PermutationIterator permutationIterator) {
        // reset the cached list
        orderArrayList.reset();
        Permutation current =  permutationIterator.next();

        CreateOrderContext createOrderContext = new CreateOrderContext(orderArrayList);
        OrderTreeIterator iter = orderTreeIterator;
        int position = 0;
        while(iter.hasNext()) {
            iter.advanceAndAddToOrder(createOrderContext,current.at(position));
            position++;
            /*
             * this is not completely correct since the order tree might have no order at a position,
             * for example for a choice. But sometimes it will contain two elements, for
             * example for barriers which we want to have both.
             * So we cap here even if the length of the permutation is not one to one
             * the number of alternating orders in the orderArrayList
             */
            if(position > interleaveLoopContext.maximumAlternatingOrders()) {
                interleaveLoopContext.maximumAlternatingOrdersCapped(permutationIterator.length());
                break;
            }
        }

        return cycleDetectionAdapter.build(orderArrayList);
    }

}
