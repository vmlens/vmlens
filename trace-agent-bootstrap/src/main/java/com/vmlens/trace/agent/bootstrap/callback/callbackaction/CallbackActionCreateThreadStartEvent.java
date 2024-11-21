package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.impl.ThreadStartEvent;

import static com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionApplyRuntimeEventFactory.applyToRuntimeEvent;

public class CallbackActionCreateThreadStartEvent implements CallbackAction {

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        ThreadStartEvent threadStartEvent = new ThreadStartEvent(threadLocalDataWhenInTest.startedThread());
        applyToRuntimeEvent(threadStartEvent, threadLocalDataWhenInTest, queueIn);
    }
}
