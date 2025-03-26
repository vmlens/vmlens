package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates;

import com.vmlens.trace.agent.bootstrap.exception.TestBlockedException;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface ActiveStrategy {
    boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);
    void checkStopWaiting(ThreadIndexAndThreadStateMap runContext) throws TestBlockedException;
}
