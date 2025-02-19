package com.vmlens.trace.agent.bootstrap.callback.callbackaction;


import com.vmlens.trace.agent.bootstrap.callback.threadlocal.InMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

/**
 * Used for pre analyzed methods to set the in method id for the runtime action
 */
public class SetInMethodIdAndPositionAtThreadLocal implements CallbackAction {

    private final InMethodIdAndPosition inMethodIdAndPosition;

    public SetInMethodIdAndPositionAtThreadLocal(InMethodIdAndPosition inMethodIdAndPosition) {
        this.inMethodIdAndPosition = inMethodIdAndPosition;
    }


    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        threadLocalDataWhenInTest.setInMethodIdAndPosition(inMethodIdAndPosition);
        return TLinkableWrapper.emptyList();
    }
}
