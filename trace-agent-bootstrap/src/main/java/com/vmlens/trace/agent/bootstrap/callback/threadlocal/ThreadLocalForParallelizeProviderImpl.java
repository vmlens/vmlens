package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class ThreadLocalForParallelizeProviderImpl implements ThreadLocalForParallelizeProvider {

    @Override
    public ThreadLocalForParallelize threadLocalForParallelize() {
        return ThreadLocalWhenInTestAdapterImpl.callbackStatePerThread.get();
    }
}
