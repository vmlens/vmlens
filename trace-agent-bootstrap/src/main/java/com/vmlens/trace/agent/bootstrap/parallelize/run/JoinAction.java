package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class JoinAction {

    private final Object taskOrPool;
    private final QueueIn queueIn;

    public JoinAction(Object taskOrPool,
                      QueueIn queueIn) {
        this.taskOrPool = taskOrPool;
        this.queueIn = queueIn;
    }

    public Object taskOrPool() {
        return taskOrPool;
    }

    public QueueIn queueIn() {
        return queueIn;
    }
}
