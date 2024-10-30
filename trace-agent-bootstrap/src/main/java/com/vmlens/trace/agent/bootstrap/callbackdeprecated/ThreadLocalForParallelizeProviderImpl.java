package com.vmlens.trace.agent.bootstrap.callbackdeprecated;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.ParallelizeBridgeForCallbackImpl;

public class ThreadLocalForParallelizeProviderImpl implements ThreadLocalForParallelizeProvider {

    @Override
    public ThreadLocalForParallelize threadLocalForParallelize() {
        return ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
    }
}
