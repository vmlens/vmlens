package com.vmlens.trace.agent.bootstrap.parallelize.run.thread;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.ThreadLocalForCallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public class ThreadLocalForParallelize extends ThreadLocalForCallbackAction {

    public static final String ANARSOFT_THREAD_NAME = "anarsoft";
    private ThreadLocalWhenInTest parallelizedThreadLocal;

    private boolean inThreadPool;

    public ThreadLocalForParallelize(ThreadForParallelize threadForParallelize) {
        super(threadForParallelize);
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

    public void setParallelizedThreadLocalToNull() {
        this.parallelizedThreadLocal = null;
    }

    public long threadId() {
        return threadForParallelize.getId();
    }

    public String threadName() {
        return threadForParallelize.getName();
    }

    public boolean inThreadPool() {
        return inThreadPool;
    }

    public void setInThreadPool(boolean inThreadPool) {
        this.inThreadPool = inThreadPool;
    }

    public ThreadForParallelize threadForParallelize() {
        return threadForParallelize;
    }
}
