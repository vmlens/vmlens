package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRunElement;

import java.util.Arrays;
import java.util.Iterator;

/**
 * ToDo sort alternatingOrderArray that equals always returns the right value
 */
public class AlternatingOrderContainer implements Iterable<CalculatedRun> {

    private class AlternatingOrderContainerIterator implements
            Iterator<CalculatedRun> {

        private final LeftBeforeRight[] currentOrder;

        private final PermutationIterator permutationIterator;

        public AlternatingOrderContainerIterator() {
            this.permutationIterator = new PermutationIterator(alternatingOrderArray.length);
            this.currentOrder = new LeftBeforeRight[alternatingOrderArray.length];
        }

        @Override
        public boolean hasNext() {
            return permutationIterator.hasNext();
        }

        @Override
        public CalculatedRun next() {
            for(int i = 0; i < alternatingOrderArray.length;i++) {
                currentOrder[i] = alternatingOrderArray[i].order(permutationIterator.at(i));
            }
            permutationIterator.advance();

            return new CreateCalculatedRunForOrder(currentOrder,actualRun).create();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }


    private final  LeftBeforeRight[] fixedOrderArray;
    private final  AlternatingOrderElement[] alternatingOrderArray;
    private final ThreadIdToElementList<CalculatedRunElement> actualRun;
    public AlternatingOrderContainer(LeftBeforeRight[] fixedOrderArray,
                                     AlternatingOrderElement[] alternatingOrderArray,
                                     ThreadIdToElementList<CalculatedRunElement> actualRun) {
        this.fixedOrderArray = fixedOrderArray;
        this.alternatingOrderArray = alternatingOrderArray;
        this.actualRun = actualRun;

        Arrays.sort(this.fixedOrderArray);
        Arrays.sort(this.alternatingOrderArray);
    }

    // For Tests
    public AlternatingOrderContainer(LeftBeforeRight[] fixedOrderArray,
                                     AlternatingOrderElement[] alternatingOrderArray) {
        this(fixedOrderArray,alternatingOrderArray,null);
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

        if (!Arrays.equals(fixedOrderArray, that.fixedOrderArray)) return false;
        return Arrays.equals(alternatingOrderArray, that.alternatingOrderArray);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(fixedOrderArray);
        result = 31 * result + Arrays.hashCode(alternatingOrderArray);
        return result;
    }
}
