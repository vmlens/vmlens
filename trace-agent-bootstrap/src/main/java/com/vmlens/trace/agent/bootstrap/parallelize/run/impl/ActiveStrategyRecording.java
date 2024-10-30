package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.ThreadLocalDataWhenInTest;

public class ActiveStrategyRecording implements ActiveStrategy {
    @Override
    public boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return true;
    }
}
