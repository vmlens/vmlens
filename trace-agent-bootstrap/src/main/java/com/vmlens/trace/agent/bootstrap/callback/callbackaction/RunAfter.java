package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFields;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;


public class RunAfter<EVENT extends RuntimeEvent> implements CallbackAction {

    private final EVENT runtimeEvent;
    private final SetFields<EVENT> setFieldsStrategy;

    public RunAfter(EVENT runtimeEvent, SetFields<EVENT> setFieldsStrategy) {
        this.runtimeEvent = runtimeEvent;
        this.setFieldsStrategy = setFieldsStrategy;
    }

    @Override
    public void  execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        setFieldsStrategy.setFields(runtimeEvent);
        threadLocalDataWhenInTest.runAdapter().after(runtimeEvent, threadLocalDataWhenInTest,queueIn);
    }
}
