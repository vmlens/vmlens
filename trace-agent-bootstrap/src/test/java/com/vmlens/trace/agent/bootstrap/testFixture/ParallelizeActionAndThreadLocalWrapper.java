package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapper;

public class ParallelizeActionAndThreadLocalWrapper {

    public final ParallelizeAction parallelizeAction;
    public final ThreadLocalWrapper threadLocalWrapper;

    public ParallelizeActionAndThreadLocalWrapper(ParallelizeAction parallelizeAction, ThreadLocalWrapper threadLocalWrapper) {
        this.parallelizeAction = parallelizeAction;
        this.threadLocalWrapper = threadLocalWrapper;
    }
}
