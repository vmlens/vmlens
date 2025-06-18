package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

public class NewTaskContext {

    private final QueueIn queueIn;
    private final ThreadWrapper newThread;
    private final ThreadLocalForParallelize threadLocalForParallelize;

    public NewTaskContext(QueueIn queueIn,
                          ThreadWrapper newThread,
                          ThreadLocalForParallelize threadLocalForParallelize) {
        this.queueIn = queueIn;
        this.newThread = newThread;
        this.threadLocalForParallelize = threadLocalForParallelize;
    }

    public QueueIn queueIn() {
        return queueIn;
    }

    public ThreadWrapper newThread() {
        return newThread;
    }

    public ThreadLocalForParallelize threadLocalForParallelize() {
        return threadLocalForParallelize;
    }
}
