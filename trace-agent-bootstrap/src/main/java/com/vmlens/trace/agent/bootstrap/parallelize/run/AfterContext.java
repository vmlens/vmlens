package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;

public class AfterContext {

    private final ThreadLocalWhenInTest threadLocalDataWhenInTest;
    private final RuntimeEvent runtimeEvent;
    private final QueueIn queueIn;

    public AfterContext(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        RuntimeEvent runtimeEvent,
                        QueueIn queueIn) {
        this.threadLocalDataWhenInTest = threadLocalDataWhenInTest;
        this.runtimeEvent = runtimeEvent;
        this.queueIn = queueIn;
    }

    public ThreadLocalWhenInTest threadLocalDataWhenInTest() {
        return threadLocalDataWhenInTest;
    }

    public RuntimeEvent runtimeEvent() {
        return runtimeEvent;
    }

    public QueueIn queueIn() {
        return queueIn;
    }
}
