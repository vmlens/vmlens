package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

public class NewTaskContext {

    private final QueueIn queueIn;
    private final RunnableOrThreadWrapper newThread;
    private final ThreadLocalForParallelize threadLocalForParallelize;

    public NewTaskContext(QueueIn queueIn,
                          RunnableOrThreadWrapper newThread,
                          ThreadLocalForParallelize threadLocalForParallelize) {
        this.queueIn = queueIn;
        this.newThread = newThread;
        this.threadLocalForParallelize = threadLocalForParallelize;
    }

    public QueueIn queueIn() {
        return queueIn;
    }

    public RunnableOrThreadWrapper newThread() {
        return newThread;
    }

    public ThreadLocalForParallelize threadLocalForParallelize() {
        return threadLocalForParallelize;
    }
}
