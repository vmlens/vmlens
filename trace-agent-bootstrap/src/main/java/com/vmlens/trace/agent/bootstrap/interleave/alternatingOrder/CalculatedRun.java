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

    public void debug(AgentLogger agentLogger) {
        // ToDo improve log format
        agentLogger.debug(Arrays.toString(calculatedRunElementArray));
    }

    // Visible for Test
    Position[] calculatedRunElementArray() {
        return calculatedRunElementArray;
    }
}
