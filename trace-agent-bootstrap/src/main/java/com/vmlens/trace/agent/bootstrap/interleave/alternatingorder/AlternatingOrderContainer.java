package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;
import com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun.CalculatedRunFactory;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;

import java.util.Iterator;


/**
 * Let you iterate over all calculated runs which can be created out of the alternating order. The iterator return null
 * when an order leads to an impossible calculated run.
 */
public class AlternatingOrderContainer implements Iterable<CalculatedRun> {

    private ThreadIndexToElementList<Position> actualRun;
    private LeftBeforeRight[] fixedOrderArray;
    private OrderTree orderTree;
    private InterleaveLoopContext interleaveLoopContext;

    public AlternatingOrderContainer(ThreadIndexToElementList<Position> actualRun,
                                     LeftBeforeRight[] fixedOrderArray,
                                     OrderTree orderTree,
                                     InterleaveLoopContext interleaveLoopContext) {
        this.actualRun = actualRun;
        this.fixedOrderArray = fixedOrderArray;
        this.orderTree = orderTree;
        this.interleaveLoopContext = interleaveLoopContext;
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

        private PermutationIterator permutationIterator;
        private CalculatedRunFactory calculatedRunFactory;

        public AlternatingOrderContainerIterator() {
            this.permutationIterator = new PermutationIterator(orderTree.length());
            this.calculatedRunFactory = new CalculatedRunFactory(fixedOrderArray,actualRun, interleaveLoopContext);
        }

        @Override
        public boolean hasNext() {
            if(permutationIterator == null) {
                return false;
            }

            boolean temp =  permutationIterator.hasNext();
            if(!temp) {
                permutationIterator = null;
                calculatedRunFactory = null;
                actualRun = null;
                fixedOrderArray = null;
                orderTree = null;
                interleaveLoopContext = null;
            }
            return temp;
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
