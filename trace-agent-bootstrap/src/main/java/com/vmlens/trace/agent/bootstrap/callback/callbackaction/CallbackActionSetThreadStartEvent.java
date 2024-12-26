package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper.emptyList;

public class CallbackActionSetThreadStartEvent implements CallbackAction {

    private final ThreadStartEvent threadStartEvent;

    private CallbackActionSetThreadStartEvent(ThreadStartEvent threadStartEvent) {
        this.threadStartEvent = threadStartEvent;
    }

    public static CallbackAction setToNull() {
        return new CallbackActionSetThreadStartEvent(null);
    }

    public static CallbackAction setTo(ThreadStartEvent threadStartEvent) {
        return new CallbackActionSetThreadStartEvent(threadStartEvent);
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        threadLocalDataWhenInTest.setThreadStartEvent(threadStartEvent);
        return emptyList();
    }
}
