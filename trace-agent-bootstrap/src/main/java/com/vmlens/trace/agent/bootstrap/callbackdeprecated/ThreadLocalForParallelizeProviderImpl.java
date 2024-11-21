package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class ThreadLocalForParallelizeProviderImpl implements ThreadLocalForParallelizeProvider {

    @Override
    public ThreadLocalForParallelize threadLocalForParallelize() {
        return ThreadLocalWhenInTestAdapterImpl.callbackStatePerThread.get();
    }
}
