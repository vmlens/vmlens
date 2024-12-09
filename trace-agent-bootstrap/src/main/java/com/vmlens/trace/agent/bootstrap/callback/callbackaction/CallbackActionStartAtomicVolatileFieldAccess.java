package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;

public class CallbackActionStartAtomicVolatileFieldAccess implements CallbackAction {

    private final AtomicOperation atomicOperation;

    public CallbackActionStartAtomicVolatileFieldAccess(AtomicOperation atomicOperation) {
        this.atomicOperation = atomicOperation;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        threadLocalDataWhenInTest.setAtomicOperation(atomicOperation);
        threadLocalDataWhenInTest.runAdapter().startAtomicOperation(threadLocalDataWhenInTest);
        return emptyList();
    }
}
