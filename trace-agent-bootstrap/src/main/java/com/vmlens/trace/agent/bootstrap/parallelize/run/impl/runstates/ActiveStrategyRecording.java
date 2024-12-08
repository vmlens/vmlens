package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;

public class ActiveStrategyRecording implements ActiveStrategy {
    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return true;
    }
}
