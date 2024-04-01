package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class ParallelizeActionAndThreadLocalWrapper {

    public final ParallelizeAction parallelizeAction;
    public final ThreadLocalForParallelize threadLocalWrapperForParallelize;

    public ParallelizeActionAndThreadLocalWrapper(ParallelizeAction parallelizeAction, ThreadLocalForParallelize threadLocalWrapperForParallelize) {
        this.parallelizeAction = parallelizeAction;
        this.threadLocalWrapperForParallelize = threadLocalWrapperForParallelize;
    }
}
