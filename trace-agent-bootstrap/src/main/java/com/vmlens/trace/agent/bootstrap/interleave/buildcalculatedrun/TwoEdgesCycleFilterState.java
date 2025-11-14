package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.CheckMinimumPositon;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

public class TwoEdgesCycleFilterState implements CheckMinimumPositon {

    private final int[] minimumPositions;

    public TwoEdgesCycleFilterState(int maxThreadIndex) {
        this.minimumPositions = new int[maxThreadIndex+1];
    }

    @Override
    public void setIfGreater(Position position) {
        int value = minimumPositions[position.threadIndex];
        if(value < position.positionInThread) {
            minimumPositions[position.threadIndex] = position.positionInThread;
        }
    }

    @Override
    public int get(int threadIndex) {
        return minimumPositions[threadIndex];
    }
}
