package com.vmlens.trace.agent.bootstrap.parallelize.run.thread;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public class ThreadLocalForParallelize {

    public static final String ANARSOFT_THREAD_NAME = "anarsoft";

    private final ThreadForParallelize threadForParallelize;

    private ThreadLocalWhenInTest parallelizedThreadLocal;
    private boolean inCallbackProcessing = false;

    public ThreadLocalForParallelize(ThreadForParallelize threadForParallelize) {
        this.threadForParallelize = threadForParallelize;
    }

    public ThreadLocalWhenInTest getThreadLocalDataWhenInTest() {
        return parallelizedThreadLocal;
    }

    public void setThreadLocalDataWhenInTest(ThreadLocalWhenInTest parallelizedThreadLocal) {
        this.parallelizedThreadLocal = parallelizedThreadLocal;
    }

    public ThreadLocalWhenInTest startCallbackProcessing() {
        if (parallelizedThreadLocal != null) {
            if (!inCallbackProcessing) {
                inCallbackProcessing = true;
                return parallelizedThreadLocal;
            }
            return null;
        }
        return null;
    }

    public void setInCallbackProcessing() {
        inCallbackProcessing = true;
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

    public void stopCallbackProcessing() {
        inCallbackProcessing = false;
    }

    public ThreadForParallelize threadForParallelize() {
        return threadForParallelize;
    }
}
