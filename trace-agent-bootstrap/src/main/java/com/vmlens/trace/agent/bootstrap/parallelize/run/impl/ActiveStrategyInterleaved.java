package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;

public class ActiveStrategyInterleaved implements ActiveStrategy {

    private final CalculatedRun calculatedRun;

    public ActiveStrategyInterleaved(CalculatedRun calculatedRun) {
        this.calculatedRun = calculatedRun;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return calculatedRun.isActive(threadLocalDataWhenInTest.threadIndex());
    }
}
