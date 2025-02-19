package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.RuntimeEventAndSetFieldsImpl;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFields;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setinmethodidandposition.RuntimeEventAndSetInMethodIdAndPositionImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithInMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;

public class RunStartAtomicAction<EVENT extends RuntimeEvent & WithInMethodIdAndPosition> implements CallbackAction {

    private final RuntimeEventAndSetFieldsImpl<EVENT> atomicOperation;

    public RunStartAtomicAction(RuntimeEventAndSetFieldsImpl<EVENT> atomicOperation) {
        this.atomicOperation = atomicOperation;
    }

    public static <EVENT extends RuntimeEvent & WithInMethodIdAndPosition> CallbackAction of(EVENT runtimeEvent,
                                                                                             SetFields<EVENT> setFieldsStrategy) {
        RuntimeEventAndSetFieldsImpl<EVENT> callbackActionForRuntimeEvent =
                new RuntimeEventAndSetFieldsImpl<>(runtimeEvent, setFieldsStrategy);
        return new RunStartAtomicAction<>(callbackActionForRuntimeEvent);
    }


    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        EVENT event = atomicOperation.applySetFields();
        threadLocalDataWhenInTest.setRuntimeEventForAtomicAction(new RuntimeEventAndSetInMethodIdAndPositionImpl<>(event));
        threadLocalDataWhenInTest.runAdapter().startAtomicOperation(threadLocalDataWhenInTest);
        return emptyList();
    }
}
