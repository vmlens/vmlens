package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;

public class ActiveStrategyInterleaved implements ActiveStrategy {

    private final CalculatedRun calculatedRun;

    public ActiveStrategyInterleaved(CalculatedRun calculatedRun) {
        this.calculatedRun = calculatedRun;
    }

    @Override
    public boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return calculatedRun.isActive(threadLocalDataWhenInTest.threadIndex());
    }
}
