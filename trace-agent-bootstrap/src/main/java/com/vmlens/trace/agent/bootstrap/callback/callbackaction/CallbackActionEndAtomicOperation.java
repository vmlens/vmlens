package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;

public class CallbackActionEndAtomicOperation implements CallbackAction {
    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        if (threadLocalDataWhenInTest.atomicOperation() == null) {
            return emptyList();
        }
        TLinkedList<TLinkableWrapper<SerializableEvent>> result =
                threadLocalDataWhenInTest.atomicOperation().execute(threadLocalDataWhenInTest);
        threadLocalDataWhenInTest.setAtomicVolatileFieldAccess(null);
        return result;
    }
}
