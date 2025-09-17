package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public class ProcessEventContext {


    private final CreateInterleaveActionContext context;
    private final ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize;
    private final SendEvent sendEvent;

    public ProcessEventContext(CreateInterleaveActionContext context,
                               ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                               SendEvent sendEvent) {
        this.context = context;
        this.threadLocalWhenInTestForParallelize = threadLocalWhenInTestForParallelize;
        this.sendEvent = sendEvent;
    }

    public CreateInterleaveActionContext context() {
        return context;
    }

    public ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize() {
        return threadLocalWhenInTestForParallelize;
    }

    public SendEvent sendEvent() {
        return sendEvent;
    }
}
