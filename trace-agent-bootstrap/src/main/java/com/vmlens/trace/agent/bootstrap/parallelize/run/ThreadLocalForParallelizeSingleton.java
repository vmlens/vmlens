package com.vmlens.trace.agent.bootstrap.parallelize.run;

public class ThreadLocalForParallelizeSingleton {
    public static final ThreadLocal<ThreadLocalForParallelize> callbackStatePerThread = new ThreadLocal<ThreadLocalForParallelize>() {
        @Override
        protected ThreadLocalForParallelize initialValue() {
            Thread currentThread = Thread.currentThread();
            return new ThreadLocalForParallelize(currentThread.getId(),
                    currentThread.getName());
        }
    };


}
