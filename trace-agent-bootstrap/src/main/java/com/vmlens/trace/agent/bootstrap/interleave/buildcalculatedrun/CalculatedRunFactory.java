package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.Pair;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.OrderCycle;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;

public class CalculatedRunFactory {

    private final OrderArrayListFactory orderArrayListFactory;
    private final CycleDetectionAdapter cycleDetectionAdapter;
    private final TwoEdgesCycleFilter twoEdgesCycleFilter = new TwoEdgesCycleFilter();
    private final THashSet<Pair<LeftBeforeRight, LeftBeforeRight>> alreadyProcessed = new THashSet<>();
    private final InterleaveLoopContext interleaveLoopContext;
    private boolean messageSend = false;

    public CalculatedRunFactory(OrderArrayListFactory orderArrayListFactory,
                                ThreadIndexToElementList<Position> actualRun, InterleaveLoopContext interleaveLoopContext) {
        this.orderArrayListFactory = orderArrayListFactory;
        this.cycleDetectionAdapter =  new CycleDetectionAdapter(new CalculatedRunBuilder(actualRun));
        this.interleaveLoopContext = interleaveLoopContext;
    }

    /**
     * can return null
     */
    public CalculatedRun create(int[] current, OrderList orderList, CycleFoundCallback cycleFoundCallback) {
        OrderArrayList orderArrayList = orderArrayListFactory.create(orderList, current);
        if(orderArrayList == null) {
           return null;
        }
        TLinkedList<TLinkableWrapper<Pair<LeftBeforeRight, LeftBeforeRight>>> cycles = twoEdgesCycleFilter.findCycle(orderArrayList);
        if(! cycles.isEmpty()) {
            TLinkedList<TLinkableWrapper<Pair<LeftBeforeRight, LeftBeforeRight>>> toBeAdded = new TLinkedList<>();
            for(TLinkableWrapper<Pair<LeftBeforeRight, LeftBeforeRight>> cycle : cycles) {
                if(! alreadyProcessed.contains(cycle.element())) {
                    toBeAdded.add(TLinkableWrapper.wrap(cycle.element()));
                }
            }
            if(! toBeAdded.isEmpty()) {
                OrderCycle[] array = new OrderCycle[toBeAdded.size()];
                int index = 0;
                for(TLinkableWrapper<Pair<LeftBeforeRight, LeftBeforeRight>> pair : toBeAdded) {
                    array[index] = new OrderCycle(pair.element().getLeft(), pair.element().getRight());
                    index++;
                }
                cycleFoundCallback.found(array);
            }
            return null;
        }

        return cycleDetectionAdapter.build(orderArrayList);
    }

}
