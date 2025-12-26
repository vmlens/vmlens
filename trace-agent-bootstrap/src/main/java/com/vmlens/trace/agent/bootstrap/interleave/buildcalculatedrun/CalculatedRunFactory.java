package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.Pair;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.PermutationIterator;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle.OrderCycle;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;

public class CalculatedRunFactory {

    private final OrderArrayListFactory orderArrayListFactory;
    private final CycleDetectionAdapter cycleDetectionAdapter;
    private final TwoEdgesCycleFilter twoEdgesCycleFilter = new TwoEdgesCycleFilter();
    private final THashSet<Pair<LeftBeforeRight, LeftBeforeRight>> alreadyProcessed = new THashSet<>();
    private OrderTree orderTree;

    public CalculatedRunFactory(OrderArrayListFactory orderArrayListFactory,
                                ThreadIndexToElementList<Position> actualRun,
                                OrderTree orderTree) {
        this.orderArrayListFactory = orderArrayListFactory;
        this.cycleDetectionAdapter =  new CycleDetectionAdapter(new CalculatedRunBuilder(actualRun));
        this.orderTree = orderTree;
    }

    /**
     * can return null
     */
    public CalculatedRun create(PermutationIterator permutationIterator) {
        OrderArrayList orderArrayList = orderArrayListFactory.create(orderTree.createIteratorAndResetOrderCycles(), permutationIterator);
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
                orderTree.avoidCycles(array);
            }
            if(orderTree.length() >= 10) {
                orderTree = orderTree.removeCycles();
                permutationIterator.setNewLength(orderTree.length());
            }

            return null;
        }

        return cycleDetectionAdapter.build(orderArrayList);
    }

}
