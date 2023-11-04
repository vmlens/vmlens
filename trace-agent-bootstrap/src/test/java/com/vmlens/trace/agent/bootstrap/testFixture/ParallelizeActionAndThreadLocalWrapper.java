package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapperForParallelize;

public class ParallelizeActionAndThreadLocalWrapper {

    public final ParallelizeAction parallelizeAction;
    public final ThreadLocalWrapperForParallelize threadLocalWrapperForParallelize;

    public ParallelizeActionAndThreadLocalWrapper(ParallelizeAction parallelizeAction, ThreadLocalWrapperForParallelize threadLocalWrapperForParallelize) {
        this.parallelizeAction = parallelizeAction;
        this.threadLocalWrapperForParallelize = threadLocalWrapperForParallelize;
    }
}
