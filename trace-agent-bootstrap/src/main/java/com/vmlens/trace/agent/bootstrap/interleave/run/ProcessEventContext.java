package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public class ProcessEventContext {


    private final ThreadIndexAndThreadStateMap context;
    private final ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize;
    private final SendEvent sendEvent;

    public ProcessEventContext(ThreadIndexAndThreadStateMap context,
                               ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                               SendEvent sendEvent) {
        this.context = context;
        this.threadLocalWhenInTestForParallelize = threadLocalWhenInTestForParallelize;
        this.sendEvent = sendEvent;
    }

    public ThreadIndexAndThreadStateMap context() {
        return context;
    }

    public ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize() {
        return threadLocalWhenInTestForParallelize;
    }

    public SendEvent sendEvent() {
        return sendEvent;
    }
}
