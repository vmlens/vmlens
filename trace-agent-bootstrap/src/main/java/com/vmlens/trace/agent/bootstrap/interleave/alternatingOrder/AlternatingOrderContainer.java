package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;

import java.util.Iterator;


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

    public void debug(AgentLogger agentLogger) {
        orderArrays.debug(agentLogger);
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

        private final LeftBeforeRight[] currentOrder;
        private final PermutationIterator permutationIterator;

        public AlternatingOrderContainerIterator() {
            this.permutationIterator = new PermutationIterator(orderArrays.alternatingOrderArray.length);
            this.currentOrder = new LeftBeforeRight[orderArrays.alternatingOrderArray.length +
                    orderArrays.fixedOrderArray.length];
        }

        @Override
        public boolean hasNext() {
            return permutationIterator.hasNext();
        }

        @Override
        public CalculatedRun next() {
            for (int i = 0; i < orderArrays.alternatingOrderArray.length; i++) {
                currentOrder[i] = orderArrays.alternatingOrderArray[i].order(permutationIterator.at(i));
            }
            for (int i = 0; i < orderArrays.fixedOrderArray.length; i++) {
                currentOrder[i + orderArrays.alternatingOrderArray.length] =
                        orderArrays.fixedOrderArray[i];
            }
            permutationIterator.advance();
            return new CreateCalculatedRun(currentOrder, actualRun).create();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}
