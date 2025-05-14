package com.vmlens.trace.agent.bootstrap.parallelize.run.thread;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public class ThreadLocalForParallelize {

    public static final String ANARSOFT_THREAD_NAME = "anarsoft";

    private final ThreadForParallelize threadForParallelize;

    private ThreadLocalWhenInTest parallelizedThreadLocal;
    private int doNotProcessCallbackCount;

    public ThreadLocalForParallelize(ThreadForParallelize threadForParallelize) {
        this.threadForParallelize = threadForParallelize;
    }

    public ThreadLocalWhenInTest getThreadLocalDataWhenInTest() {
        return parallelizedThreadLocal;
    }

    public void setThreadLocalDataWhenInTest(ThreadLocalWhenInTest parallelizedThreadLocal) {
        this.parallelizedThreadLocal = parallelizedThreadLocal;
    }

    public ThreadLocalWhenInTest getThreadLocalWhenInTest() {
        return parallelizedThreadLocal;
    }

    public boolean canProcessCallback() {
        return doNotProcessCallbackCount == 0;
    }

    public void incrementDoNotProcessCallbackCount() {
        doNotProcessCallbackCount++;
    }

    public void resetProcessCallbackCount() {
        doNotProcessCallbackCount = 0;
    }

    public void setParallelizedThreadLocalToNull() {
        this.parallelizedThreadLocal = null;
    }

    public long threadId() {
        return threadForParallelize.getId();
    }

    public String threadName() {
        return threadForParallelize.getName();
    }


    public void decrementDoNotProcessCallbackCount() {
        doNotProcessCallbackCount--;
    }

    public ThreadForParallelize threadForParallelize() {
        return threadForParallelize;
    }
}
