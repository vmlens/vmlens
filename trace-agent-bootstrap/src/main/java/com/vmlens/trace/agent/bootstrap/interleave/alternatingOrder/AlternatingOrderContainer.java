package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.TIntList;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;

import java.util.Iterator;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.toArray;


/**
 * Let you iterate over all calculated runs which can be created out of the alternating order. The iterator return null
 * when an order leads to an impossible calculated run.
 */
public class AlternatingOrderContainer implements Iterable<CalculatedRun> {

    private final ThreadIndexToElementList<Position> actualRun;
    private final OrderArrays orderArrays;

    public AlternatingOrderContainer(OrderArrays orderArrays, ThreadIndexToElementList<Position> actualRun) {
        this.orderArrays = orderArrays;
        this.actualRun = actualRun;
    }

    /**
     * @return
     * Iterator can return null, will be filtered by InterleaveLoopIteratorStateAlternatingOrderContainer
     */
    @Override
    public Iterator<CalculatedRun> iterator() {
        return new AlternatingOrderContainerIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlternatingOrderContainer that = (AlternatingOrderContainer) o;
        return orderArrays.equals(that.orderArrays);
    }

    @Override
    public int hashCode() {
        return orderArrays.hashCode();
    }

    @Override
    public String toString() {
        return "AlternatingOrderContainer{" +
                "actualRun=" + actualRun +
                ", orderArrays=" + orderArrays +
                '}';
    }

    private class AlternatingOrderContainerIterator implements
            Iterator<CalculatedRun> {


        private final THashSet<CalculatedRun> alreadyExecuted = new THashSet<CalculatedRun>();
        private final PermutationIterator permutationIterator;

        public AlternatingOrderContainerIterator() {
            this.permutationIterator = new PermutationIterator(orderArrays.alternatingOrderArray.length +
                    orderArrays.potentialConstraints.length);

        }

        @Override
        public boolean hasNext() {
            return permutationIterator.hasNext();
        }


        /**
         * can return null
         * @return
         */
        @Override
        public CalculatedRun next() {
            TLinkedList<TLinkableWrapper<LeftBeforeRight>> newOrder = new TLinkedList<>();
            CreateOrderContext createOrderContext = new CreateOrderContext(newOrder);

            for (int i = 0; i < orderArrays.potentialConstraints.length; i++) {
                if(permutationIterator.at(i)) {
                    orderArrays.potentialConstraints[i].addConstraint(createOrderContext);
                }
            }

            int startOfAlternating = orderArrays.potentialConstraints.length;
            for (int i = 0; i < orderArrays.alternatingOrderArray.length; i++) {
                orderArrays.alternatingOrderArray[i].addOrder(createOrderContext,
                        permutationIterator.at(i+startOfAlternating));
            }

            for (int i = 0; i < orderArrays.fixedOrderArray.length; i++) {
                newOrder.add(wrap(orderArrays.fixedOrderArray[i]));
            }

            permutationIterator.advance();

            LeftBeforeRight[] orderArray = toArray(LeftBeforeRight.class, newOrder);
            CalculatedRun run =  new CreateCalculatedRun(orderArray, actualRun).create();
            if(run == null) {
                return run;
            }
            if(alreadyExecuted.contains(run)){
                return null;
            }
            alreadyExecuted.add(run);
            return run;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}
