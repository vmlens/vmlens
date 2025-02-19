package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;

public class RunEndAtomicAction implements CallbackAction {

    private final int inMethodId;
    private final int position;

    public RunEndAtomicAction(int inMethodId, int position) {
        this.inMethodId = inMethodId;
        this.position = position;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        if (threadLocalDataWhenInTest.runtimeEventForAtomicAction() == null) {
            return emptyList();
        }
        TLinkedList<TLinkableWrapper<SerializableEvent>> result = threadLocalDataWhenInTest.runAdapter().endAtomicOperation(
                threadLocalDataWhenInTest.runtimeEventForAtomicAction().setInMethodIdAndPosition(inMethodId, position),
                threadLocalDataWhenInTest);
        threadLocalDataWhenInTest.setRuntimeEventForAtomicAction(null);
        return result;
    }
}
