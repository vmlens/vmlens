package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArrays;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.AgentLogger;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;

import java.util.Iterator;
;


public class AlternatingOrderContainer implements Iterable<CalculatedRun> {

    private class AlternatingOrderContainerIterator implements
            Iterator<CalculatedRun> {

        private final LeftBeforeRight[] currentOrder;

        private final PermutationIterator permutationIterator;

        public AlternatingOrderContainerIterator() {
            this.permutationIterator = new PermutationIterator(orderArrays.alternatingOrderArray.length);
            this.currentOrder = new LeftBeforeRight[orderArrays.alternatingOrderArray.length];
        }

        @Override
        public boolean hasNext() {
            return permutationIterator.hasNext();
        }

        @Override
        public CalculatedRun next() {
            for(int i = 0; i < orderArrays.alternatingOrderArray.length;i++) {
                currentOrder[i] = orderArrays.alternatingOrderArray[i].order(permutationIterator.at(i));
            }
            permutationIterator.advance();

            return new CreateCalculatedRunForOrder(currentOrder,actualRun).create();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    private final OrderArrays orderArrays;
    private final ThreadIdToElementList<ElementAndPosition<Object>> actualRun;
    public AlternatingOrderContainer(OrderArrays orderArrays, ThreadIdToElementList<ElementAndPosition<Object>> actualRun) {
        this.orderArrays = orderArrays;
        this.actualRun = actualRun;
    }


    /**
     * @rule Iterator can return null, will be filtered by InterleaveLoopIteratorStateAlternatingOrderContainer
     * @return
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
}
