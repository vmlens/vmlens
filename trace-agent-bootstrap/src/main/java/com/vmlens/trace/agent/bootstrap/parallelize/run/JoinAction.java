package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class JoinAction {

    private final Object taskOrPool;
    private final QueueIn queueIn;
    private final ThreadLocalWhenInTest threadLocalDataWhenInTest;
    private final int inMethodId;
    private final int position;

    public JoinAction(Object taskOrPool,
                      QueueIn queueIn,
                      ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        this.taskOrPool = taskOrPool;
        this.queueIn = queueIn;
        this.threadLocalDataWhenInTest = threadLocalDataWhenInTest;
        this.inMethodId = threadLocalDataWhenInTest.inMethodIdAndPosition().inMethodId();
        this.position = threadLocalDataWhenInTest.inMethodIdAndPosition().position();
    }

    public Object taskOrPool() {
        return taskOrPool;
    }

    public QueueIn queueIn() {
        return queueIn;
    }

    public ThreadLocalWhenInTest threadLocalDataWhenInTest() {
        return threadLocalDataWhenInTest;
    }

    public int inMethodId() {
        return inMethodId;
    }

    public int position() {
        return position;
    }
}
