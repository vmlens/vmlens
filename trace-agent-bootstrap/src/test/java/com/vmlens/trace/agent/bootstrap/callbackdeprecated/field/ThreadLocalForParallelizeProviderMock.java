package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;

import com.vmlens.trace.agent.bootstrap.callbackdeprecated.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class ThreadLocalForParallelizeProviderMock implements ThreadLocalForParallelizeProvider {

    private final ThreadLocalForParallelize threadLocalForParallelize;

    public ThreadLocalForParallelizeProviderMock(ThreadLocalForParallelize threadLocalForParallelize) {
        this.threadLocalForParallelize = threadLocalForParallelize;
    }

    @Override
    public ThreadLocalForParallelize threadLocalForParallelize() {
        return threadLocalForParallelize;
    }
}
