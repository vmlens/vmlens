package com.vmlens.trace.agent.bootstrap.parallelize.threadlocal;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.impl.ThreadStartEvent;

import static com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.CallbackActionApplyRuntimeEventFactory.applyToRuntimeEvent;

public class CallbackActionCreateThreadStartEvent implements CallbackAction {

    @Override
    public void execute(ThreadLocalDataWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        ThreadStartEvent threadStartEvent = new ThreadStartEvent(threadLocalDataWhenInTest.startedThread());
        applyToRuntimeEvent(threadStartEvent, threadLocalDataWhenInTest, queueIn);
    }
}
