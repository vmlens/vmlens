package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public class ActiveStrategyRecording implements ActiveStrategy {
    @Override
    public boolean isActive(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return true;
    }
}
