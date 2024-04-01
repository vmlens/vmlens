package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;


public class CallbackStatePerThreadForParallelize implements ThreadLocalForParallelize {

    public static final String ANARSOFT_THREAD_NAME = "anarsoft";

    private final AnarsoftWeakHashMap<Object> arraysInThisThread = new AnarsoftWeakHashMap<Object>();
    private final QueueIn queueIn;
    private final long threadId;

    private ThreadLocalDataWhenInTest parallelizedThreadLocal;


    public CallbackStatePerThreadForParallelize(long threadId, QueueIn queueIn) {
        this.queueIn = queueIn;
        this.threadId = threadId;
    }

    @Override
    public ThreadLocalDataWhenInTest getThreadLocalDataWhenInTest() {
        return parallelizedThreadLocal;
    }

    @Override
    public void setThreadLocalDataWhenInTest(ThreadLocalDataWhenInTest parallelizedThreadLocal) {
        this.parallelizedThreadLocal = parallelizedThreadLocal;
    }

    @Override
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

    @Override
    public void setParallelizedThreadLocalToNull() {
        this.parallelizedThreadLocal = null;
    }

    @Override
    public ThreadLocalDataWhenInTest createNewParallelizedThreadLocal(Run run, int maxThreadIndex) {
        return null;
    }
}
