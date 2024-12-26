package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper.emptyList;

public class CallbackActionAfterMethodCall implements CallbackAction {

    private final int inMethodId;
    private final int position;

    public CallbackActionAfterMethodCall(int inMethodId, int position) {
        this.inMethodId = inMethodId;
        this.position = position;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        ThreadStartEvent threadStart = threadLocalDataWhenInTest.threadStartEvent();
        threadLocalDataWhenInTest.setThreadStartEvent(null);
        if (threadStart != null) {
            return threadLocalDataWhenInTest.after(threadStart);
        }
        return emptyList();
    }
}
