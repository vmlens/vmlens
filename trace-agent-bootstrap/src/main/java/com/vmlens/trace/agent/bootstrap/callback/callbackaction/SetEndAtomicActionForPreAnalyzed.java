package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setinmethodidandposition.RuntimeEventAndSetInMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class SetEndAtomicActionForPreAnalyzed implements CallbackAction {

    private final RuntimeEventAndSetInMethodIdAndPosition runtimeEventAndSetInMethodIdAndPosition;

    public SetEndAtomicActionForPreAnalyzed(RuntimeEventAndSetInMethodIdAndPosition
                                                    runtimeEventAndSetInMethodIdAndPosition) {
        this.runtimeEventAndSetInMethodIdAndPosition = runtimeEventAndSetInMethodIdAndPosition;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        threadLocalDataWhenInTest.setRuntimeEventForAtomicAction(runtimeEventAndSetInMethodIdAndPosition);
        return TLinkableWrapper.emptyList();
    }
}
