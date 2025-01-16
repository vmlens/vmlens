package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class CallbackActionMethodExit implements CallbackAction {

    private final int methodId;

    public CallbackActionMethodExit(int methodId) {
        this.methodId = methodId;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        MethodExitEvent methodExitEvent = new MethodExitEvent(methodId);
        return threadLocalDataWhenInTest.runAdapter().after(methodExitEvent, threadLocalDataWhenInTest);
    }
}
