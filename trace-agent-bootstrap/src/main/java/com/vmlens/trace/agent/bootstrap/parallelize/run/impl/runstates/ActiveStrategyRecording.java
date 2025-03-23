package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates;

import com.vmlens.trace.agent.bootstrap.parallelize.run.TestBlockedException;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public class ActiveStrategyRecording implements ActiveStrategy {
    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return true;
    }

    @Override
    public void checkStopWaiting(ThreadIndexAndThreadStateMap runContext) throws TestBlockedException {

    }
}
