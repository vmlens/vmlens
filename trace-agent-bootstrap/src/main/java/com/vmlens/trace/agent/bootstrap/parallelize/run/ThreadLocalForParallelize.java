package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.AnarsoftWeakHashMap;

public class ThreadLocalForParallelize {


    public static final String ANARSOFT_THREAD_NAME = "anarsoft";
    private final long threadId;
    private final AnarsoftWeakHashMap<Object> arraysInThisThread = new AnarsoftWeakHashMap<Object>();
    private ThreadLocalDataWhenInTest parallelizedThreadLocal;


    public ThreadLocalForParallelize(long threadId) {
        this.threadId = threadId;
    }


    public ThreadLocalDataWhenInTest getThreadLocalDataWhenInTest() {
        return parallelizedThreadLocal;
    }


    public void setThreadLocalDataWhenInTest(ThreadLocalDataWhenInTest parallelizedThreadLocal) {
        this.parallelizedThreadLocal = parallelizedThreadLocal;
    }

    public ThreadLocalDataWhenInTest startCallbackProcessing() {
        if (parallelizedThreadLocal != null) {
            return parallelizedThreadLocal.startCallbackProcessing();
        }
        return null;
    }


    public void setParallelizedThreadLocalToNull() {
        this.parallelizedThreadLocal = null;
    }

    public long threadId() {
        return threadId;
    }
}
