package com.vmlens.trace.agent.bootstrap.parallelize.run;

public class ThreadLocalForParallelizeSingleton {
    public static final ThreadLocal<ThreadLocalForParallelize> callbackStatePerThread = new ThreadLocal<ThreadLocalForParallelize>() {
        @Override
        protected ThreadLocalForParallelize initialValue() {
            return new ThreadLocalForParallelize(Thread.currentThread().getId());
        }
    };


}
