package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.CreateOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeIterator;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.toArray;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


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

        public AlternatingOrderContainerIterator() {
            this.permutationIterator = new PermutationIterator(orderTree.length());

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
            TLinkedList<TLinkableWrapper<LeftBeforeRight>> newOrder = new TLinkedList<>();
            CreateOrderContext createOrderContext = new CreateOrderContext(newOrder);

            OrderTreeIterator iter = orderTree.iterator();
            int position = 0;
            while(iter.hasNext()) {
                iter.advanceAndAddToOrder(createOrderContext,permutationIterator.at(position));
                position++;
            }

            for (int i = 0; i < fixedOrderArray.length; i++) {
                newOrder.add(wrap(fixedOrderArray[i]));
            }

            permutationIterator.advance();

            LeftBeforeRight[] orderArray = toArray(LeftBeforeRight.class, newOrder);
            return  new CreateCalculatedRun(orderArray, actualRun).create();
        }

    }
}
