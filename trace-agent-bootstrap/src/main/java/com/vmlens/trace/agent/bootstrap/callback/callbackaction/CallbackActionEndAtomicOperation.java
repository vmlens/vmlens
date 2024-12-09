package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class CallbackActionEndAtomicOperation implements CallbackAction {
    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        TLinkedList<TLinkableWrapper<SerializableEvent>> result =
                threadLocalDataWhenInTest.atomicOperation().execute(threadLocalDataWhenInTest);
        threadLocalDataWhenInTest.setAtomicOperation(null);
        return result;
    }
}
