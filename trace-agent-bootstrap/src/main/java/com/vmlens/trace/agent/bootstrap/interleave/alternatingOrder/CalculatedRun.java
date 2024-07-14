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
}
