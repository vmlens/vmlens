package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ParallelizeActionAfter;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;

public class AfterContextForStateMachine {

    private final ThreadLocalWhenInTest threadLocalDataWhenInTest;
    private final ParallelizeActionAfter runtimeEvent;
    private final QueueIn queueIn;

    public AfterContextForStateMachine(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                       ParallelizeActionAfter runtimeEvent,
                                       QueueIn queueIn) {
        this.threadLocalDataWhenInTest = threadLocalDataWhenInTest;
        this.runtimeEvent = runtimeEvent;
        this.queueIn = queueIn;
    }

    public static AfterContextForStateMachine of(AfterContext other) {
        return new AfterContextForStateMachine(
                other.threadLocalDataWhenInTest(),
                other.runtimeEvent(),
                other.queueIn()
        );
    }

    public ThreadLocalWhenInTest threadLocalDataWhenInTest() {
        return threadLocalDataWhenInTest;
    }

    public ParallelizeActionAfter runtimeEvent() {
        return runtimeEvent;
    }

    public QueueIn queueIn() {
        return queueIn;
    }
}
