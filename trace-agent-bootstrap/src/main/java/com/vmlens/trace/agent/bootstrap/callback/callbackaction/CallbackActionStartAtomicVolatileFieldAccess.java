package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;

public class CallbackActionStartAtomicVolatileFieldAccess implements CallbackAction {

    private final RuntimeEventAndSetFieldsStrategy atomicOperation;

    public CallbackActionStartAtomicVolatileFieldAccess(RuntimeEventAndSetFieldsStrategy atomicOperation) {
        this.atomicOperation = atomicOperation;
    }

    public static <EVENT extends RuntimeEvent> CallbackAction of(EVENT runtimeEvent,
                                                                 SetFieldsStrategy<EVENT> setFieldsStrategy) {
        RuntimeEventAndSetFieldsStrategyImpl<EVENT> callbackActionForRuntimeEvent =
                new RuntimeEventAndSetFieldsStrategyImpl<>(runtimeEvent, setFieldsStrategy);
        return new CallbackActionStartAtomicVolatileFieldAccess(callbackActionForRuntimeEvent);
    }


    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        threadLocalDataWhenInTest.setAtomicVolatileFieldAccess(atomicOperation);
        threadLocalDataWhenInTest.runAdapter().startAtomicOperation(threadLocalDataWhenInTest);
        return emptyList();
    }
}
