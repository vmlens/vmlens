package com.vmlens.trace.agent.bootstrap.parallelize.run.thread;

public class ThreadLocalForParallelizeSingleton {

    public static final ThreadLocal<ThreadLocalForParallelize> callbackStatePerThread = new ThreadLocal<ThreadLocalForParallelize>() {
        @Override
        protected ThreadLocalForParallelize initialValue() {
            Thread currentThread = Thread.currentThread();
            return new ThreadLocalForParallelize(new ThreadForParallelize(currentThread));
        }
    };

    public static void startProcess() {
        callbackStatePerThread.get().incrementDoNotProcessCallbackCount();
    }

    public static void stopProcess() {
        callbackStatePerThread.get().decrementDoNotProcessCallbackCount();;
    }

    public static boolean canProcess() {
        return callbackStatePerThread.get().canProcessCallback();
    }


}
