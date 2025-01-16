package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelizeSingleton.callbackStatePerThread;

public class ThreadLocalForParallelizeProviderImpl implements ThreadLocalForParallelizeProvider {

    @Override
    public ThreadLocalForParallelize threadLocalForParallelize() {
        return callbackStatePerThread.get();
    }
}
