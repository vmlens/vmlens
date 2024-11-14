package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalDataWhenInTest;

public interface ActiveStrategy {
    boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest);
}
