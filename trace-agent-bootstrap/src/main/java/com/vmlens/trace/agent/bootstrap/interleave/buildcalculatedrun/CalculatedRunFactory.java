package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.Either;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.Permutation;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.PermutationIterator;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.CreateOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeIterator;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;

public class CalculatedRunFactory {

    private final ThreadIndexToElementList<Position> actualRun;
    private final OrderArrayList orderArrayList;
    private Cycle cycle;

    public CalculatedRunFactory(LeftBeforeRight[] fixedOrderArray,
                                ThreadIndexToElementList<Position> actualRun) {
        this.orderArrayList = new OrderArrayList(fixedOrderArray);
        this.actualRun = actualRun;
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
        }


        cycle = null;
        CycleDetectionAdapter cycleDetectionAdapter = new CycleDetectionAdapter(new CalculatedRunBuilder(actualRun));
        Either<CalculatedRun, Cycle> runOrCycle = cycleDetectionAdapter.build(orderArrayList);

        if(runOrCycle.getLeft() != null) {
            return runOrCycle.getLeft();
        }

         cycle = runOrCycle.getRight();
         return null;
    }

}
