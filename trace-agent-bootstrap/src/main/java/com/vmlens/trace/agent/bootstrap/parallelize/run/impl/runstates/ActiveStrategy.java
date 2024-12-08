package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;

public interface ActiveStrategy {
    boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);
}
