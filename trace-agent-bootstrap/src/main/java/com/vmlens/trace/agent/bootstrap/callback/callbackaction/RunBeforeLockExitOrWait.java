package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFields;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.LockExitOrWaitEvent;

public class RunBeforeLockExitOrWait<EVENT extends LockExitOrWaitEvent> implements CallbackAction {

    private final EVENT runtimeEvent;
    private final SetFields<EVENT> setFieldsStrategy;

    public RunBeforeLockExitOrWait(EVENT runtimeEvent, SetFields<EVENT> setFieldsStrategy) {
        this.runtimeEvent = runtimeEvent;
        this.setFieldsStrategy = setFieldsStrategy;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        setFieldsStrategy.setFields(runtimeEvent,threadLocalDataWhenInTest);
        threadLocalDataWhenInTest.runAdapter().beforeLockExitOrWait(runtimeEvent,threadLocalDataWhenInTest,queueIn);
    }
}
