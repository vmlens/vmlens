package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.NotInAtomicCallbackStrategy;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.WithoutAtomic;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFields;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;


public class RunAfter<EVENT extends RuntimeEvent> implements CallbackAction {

    private final EVENT runtimeEvent;
    private final SetFields<EVENT> setFieldsStrategy;
    private final NotInAtomicCallbackStrategy notInAtomicCallbackStrategy = new WithoutAtomic();

    public RunAfter(EVENT runtimeEvent, SetFields<EVENT> setFieldsStrategy) {
        this.runtimeEvent = runtimeEvent;
        this.setFieldsStrategy = setFieldsStrategy;
    }

    @Override
    public void  execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                         QueueIn queueIn) {
        setFieldsStrategy.setFields(runtimeEvent,threadLocalDataWhenInTest);
        threadLocalDataWhenInTest.runAdapter().after(
                new AfterContext(threadLocalDataWhenInTest,runtimeEvent,queueIn));
    }


    @Override
    public boolean notInAtomicCallback(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return notInAtomicCallbackStrategy.notInAtomicCallback(threadLocalDataWhenInTest);
    }
}
