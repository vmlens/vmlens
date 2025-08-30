package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ParallelizeActionAfter;

public class AfterContextForStateMachine {

    private final ThreadLocalWhenInTest threadLocalDataWhenInTest;
    private final ParallelizeActionAfter runtimeEvent;


    public AfterContextForStateMachine(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                       ParallelizeActionAfter runtimeEvent) {
        this.threadLocalDataWhenInTest = threadLocalDataWhenInTest;
        this.runtimeEvent = runtimeEvent;
    }

    public static AfterContextForStateMachine of(AfterContext other) {
        return new AfterContextForStateMachine(
                other.threadLocalDataWhenInTest(),
                other.runtimeEvent()
        );
    }

    public ThreadLocalWhenInTest threadLocalDataWhenInTest() {
        return threadLocalDataWhenInTest;
    }

    public ParallelizeActionAfter runtimeEvent() {
        return runtimeEvent;
    }


}
