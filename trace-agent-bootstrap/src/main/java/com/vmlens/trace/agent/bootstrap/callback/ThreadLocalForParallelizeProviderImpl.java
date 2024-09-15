package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class ThreadLocalForParallelizeProviderImpl implements ThreadLocalForParallelizeProvider {

    @Override
    public ThreadLocalForParallelize threadLocalForParallelize() {
        return CallbackState.callbackStatePerThread.get();
    }
}
