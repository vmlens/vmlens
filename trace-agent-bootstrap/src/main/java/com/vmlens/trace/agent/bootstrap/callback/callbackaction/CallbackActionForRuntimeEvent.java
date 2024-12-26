package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;


public class CallbackActionForRuntimeEvent<EVENT extends RuntimeEvent> implements CallbackAction{

    private final EVENT runtimeEvent;
    private final SetFieldsStrategy<EVENT> setFieldsStrategy;

    public CallbackActionForRuntimeEvent(EVENT runtimeEvent, SetFieldsStrategy<EVENT> setFieldsStrategy) {
        this.runtimeEvent = runtimeEvent;
        this.setFieldsStrategy = setFieldsStrategy;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        setFieldsStrategy.setFields(runtimeEvent);
        return threadLocalDataWhenInTest.after(runtimeEvent);
    }
}
