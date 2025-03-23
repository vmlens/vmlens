package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;

import java.util.Arrays;

public class CalculatedRun {

    private final Position[] calculatedRunElementArray;
    private final ThreadIndexToElementList<Position> calculatedRunPerThread;
    private int currentPosInArray = 0;

    public CalculatedRun(Position[] calculatedRunElementArray) {
        this.calculatedRunElementArray = calculatedRunElementArray;
        this.calculatedRunPerThread = new ThreadIndexToElementList<>();
        for(Position position : calculatedRunElementArray) {
            calculatedRunPerThread.add(position);
        }
    }

    public boolean isActive(int threadIndex) {
        if (currentPosInArray >= calculatedRunElementArray.length) {
            return true;
        }
        /*
          The last after call of a thread needs to be enabled
          for example:
              Thread0.run
              volatile read
          has only one interleave action in the array for the volatile read
          but the after for the volatile field should not block
         */
        if(calculatedRunPerThread.isEmptyAtIndex(threadIndex)) {
            return true;
        }

        return calculatedRunElementArray[currentPosInArray].threadIndex() == threadIndex;
    }

    public void incrementPositionInThread(int threadIndex) {
        currentPosInArray++;
        calculatedRunPerThread.popIfNotEmpty(threadIndex);
    }

    // Visible for Test
    public Position[] calculatedRunElementArray() {
        return calculatedRunElementArray;
    }

    @Override
    public String toString() {
        return "CalculatedRun{" +
                "calculatedRunElementArray=" + Arrays.toString(calculatedRunElementArray) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CalculatedRun that = (CalculatedRun) o;
        return Arrays.equals(calculatedRunElementArray, that.calculatedRunElementArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(calculatedRunElementArray);
    }

    public Integer activeThreadIndex() {
        if (currentPosInArray >= calculatedRunElementArray.length) {
            return null;
        }
        return calculatedRunElementArray[currentPosInArray].threadIndex();
    }
}
