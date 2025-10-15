package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.FirstMethodInThread;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

public class NewTaskContext {

    private final QueueIn queueIn;
    private final ThreadWrapper newThread;
    private final ThreadLocalForParallelize threadLocalForParallelize;
    private final FirstMethodInThread firstMethodInThread;

    public NewTaskContext(QueueIn queueIn,
                          ThreadWrapper newThread,
                          ThreadLocalForParallelize threadLocalForParallelize,
                          FirstMethodInThread firstMethodInThread) {
        this.queueIn = queueIn;
        this.newThread = newThread;
        this.threadLocalForParallelize = threadLocalForParallelize;
        this.firstMethodInThread = firstMethodInThread;
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

    public FirstMethodInThread firstMethodInThread() {
        return firstMethodInThread;
    }
}
