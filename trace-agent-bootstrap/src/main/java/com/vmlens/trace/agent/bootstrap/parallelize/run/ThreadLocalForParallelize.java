package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public class ThreadLocalForParallelize {

    public static final String ANARSOFT_THREAD_NAME = "anarsoft";
    private final long threadId;
    private final String threadName;
    private ThreadLocalWhenInTest parallelizedThreadLocal;
    private boolean inCallbackProcessing = false;

    public ThreadLocalForParallelize(long threadId, String threadName) {
        this.threadId = threadId;
        this.threadName = threadName;
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
        return threadId;
    }

    public String threadName() {
        return threadName;
    }

    public void stopCallbackProcessing() {
        inCallbackProcessing = false;
    }
}
