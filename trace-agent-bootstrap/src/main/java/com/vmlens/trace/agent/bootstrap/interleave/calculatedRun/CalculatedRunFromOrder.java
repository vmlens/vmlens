package com.vmlens.trace.agent.bootstrap.interleave.calculatedRun;

import java.util.Arrays;

public class CalculatedRunFromOrder implements CalculatedRun {


    private final ElementAndPosition<Object>[] calculatedRunElementArray;
    private int currentPosInArray = 0;
    public CalculatedRunFromOrder(ElementAndPosition<Object>[] calculatedRunElementArray) {
        this.calculatedRunElementArray = calculatedRunElementArray;
    }
    @Override
    public boolean isActive(int threadIndex) {
        return calculatedRunElementArray[currentPosInArray].threadIndex() == threadIndex;
    }
    @Override
    public void incrementPositionInThread() {
        currentPosInArray++;
    }

    @Override
    public void debug(AgentLogger agentLogger) {
        // ToDo improve log format
        agentLogger.debug(Arrays.toString(calculatedRunElementArray));
    }
}
