package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.Arrays;

public class CalculatedRun {

    private final Position[] calculatedRunElementArray;
    private int currentPosInArray = 0;

    public CalculatedRun(Position[] calculatedRunElementArray) {
        this.calculatedRunElementArray = calculatedRunElementArray;
    }

    public boolean isActive(int threadIndex) {
        if (currentPosInArray >= calculatedRunElementArray.length) {
            return true;
        }
        return calculatedRunElementArray[currentPosInArray].threadIndex() == threadIndex;
    }

    public void incrementPositionInThread() {
        currentPosInArray++;
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
}
