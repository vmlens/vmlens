package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveInfo;

public class ActualRunMockStrategyTake implements ActualRunMockStrategy {

    private int runPosition;

    @Override
    public InterleaveInfo cteateInterleaveInfo() {
        int temp = runPosition;
        runPosition++;
        return new InterleaveInfo(temp);
    }
}
