package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

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
    Position[] calculatedRunElementArray() {
        return calculatedRunElementArray;
    }
}
