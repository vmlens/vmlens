package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelizeProvider;

public class CallbackState implements ThreadLocalForParallelizeProvider {

    public static final EventQueue eventQueue = new EventQueue();

    // Fixme  make private
    public static final ThreadLocal<ThreadLocalForParallelize> callbackStatePerThread = new ThreadLocal<ThreadLocalForParallelize>() {
        @Override
        protected ThreadLocalForParallelize initialValue() {
            return new ThreadLocalForParallelize(Thread.currentThread().getId(), eventQueue);
        }
    };


    @Override
    public ThreadLocalForParallelize getThreadLocalForParallelize() {
        return callbackStatePerThread.get();
    }
}
