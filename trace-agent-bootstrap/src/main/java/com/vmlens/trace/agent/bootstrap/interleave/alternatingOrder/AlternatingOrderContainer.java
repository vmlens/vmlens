package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;

import java.util.Iterator;


/**
 * Let you iterate over all calculated runs which can be created out of the alternating order. The iterator return null
 * when an order leads to an impossible calculated run.
 */
public class AlternatingOrderContainer implements Iterable<CalculatedRun> {

    private final ThreadIndexToElementList<Position> actualRun;
    private final LeftBeforeRight[] fixedOrderArray;
    private final OrderTree orderTree;

    public AlternatingOrderContainer(ThreadIndexToElementList<Position> actualRun,
                                     LeftBeforeRight[] fixedOrderArray,
                                     OrderTree orderTree) {
        this.actualRun = actualRun;
        this.fixedOrderArray = fixedOrderArray;
        this.orderTree = orderTree;
    }

    /**
     * Iterator can return null, will be filtered by InterleaveLoopIteratorStateAlternatingOrderContainer
     */
    @Override
    public Iterator<CalculatedRun> iterator() {
        return new AlternatingOrderContainerIterator();
    }



    private class AlternatingOrderContainerIterator implements
            Iterator<CalculatedRun> {

        private final PermutationIterator permutationIterator;
        private final CalculatedRunFactory calculatedRunFactory;

        public AlternatingOrderContainerIterator() {
            this.permutationIterator = new PermutationIterator(orderTree.length());
            this.calculatedRunFactory = new CalculatedRunFactory(fixedOrderArray,actualRun);

        }

        @Override
        public boolean hasNext() {
            return permutationIterator.hasNext();
        }

        /**
         * can return null
         */
        @Override
        public CalculatedRun next() {
            return calculatedRunFactory.create(orderTree.iterator(),permutationIterator);
        }

    }
}
