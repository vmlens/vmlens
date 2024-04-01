package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.AnarsoftWeakHashMap;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;

public class ThreadLocalForParallelize {


    public static final String ANARSOFT_THREAD_NAME = "anarsoft";

    private final AnarsoftWeakHashMap<Object> arraysInThisThread = new AnarsoftWeakHashMap<Object>();
    private final QueueIn queueIn;
    private final long threadId;

    private ThreadLocalDataWhenInTest parallelizedThreadLocal;


    public ThreadLocalForParallelize(long threadId, QueueIn queueIn) {
        this.queueIn = queueIn;
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

    public QueueIn queueIn() {
        return queueIn;
    }

    public long threadId() {
        return threadId;
    }


    public void setParallelizedThreadLocalToNull() {
        this.parallelizedThreadLocal = null;
    }


    public ThreadLocalDataWhenInTest createNewParallelizedThreadLocal(Run run, int threadIndex) {
        this.parallelizedThreadLocal = new ThreadLocalDataWhenInTest(run, threadIndex, queueIn, threadId);
        return parallelizedThreadLocal;
    }
}
