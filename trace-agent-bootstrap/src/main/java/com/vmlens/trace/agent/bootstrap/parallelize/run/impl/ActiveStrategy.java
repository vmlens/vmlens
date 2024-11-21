package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public interface ActiveStrategy {
    boolean isActive(ThreadLocalWhenInTest threadLocalDataWhenInTest);
}
