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
import gnu.trove.set.hash.THashSet;

public class CalculatedRunFactory {

    private final CycleDetectionAdapter cycleDetectionAdapter;
    private final OrderArrayList orderArrayList;
    private final THashSet<Position> startingPoints = new THashSet<>();

    public CalculatedRunFactory(LeftBeforeRight[] fixedOrderArray,
                                ThreadIndexToElementList<Position> actualRun) {
        this.orderArrayList = new OrderArrayList(fixedOrderArray);
        this.cycleDetectionAdapter =  new CycleDetectionAdapter(new CalculatedRunBuilder(actualRun));;
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


        Either<CalculatedRun, THashSet<Position>> runOrCycle = cycleDetectionAdapter.build(orderArrayList,startingPoints);
        if(runOrCycle.getLeft() != null) {
            return runOrCycle.getLeft();
        }
         startingPoints.addAll(runOrCycle.getRight());
         return null;
    }

}
