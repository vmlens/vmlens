package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit.ExecuteAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class SetExecuteAfterMethodCall implements CallbackAction {

    private final ExecuteAfterMethodCall runtimeEventAndSetInMethodIdAndPosition;

    public SetExecuteAfterMethodCall(ExecuteAfterMethodCall
                                                    runtimeEventAndSetInMethodIdAndPosition) {
        this.runtimeEventAndSetInMethodIdAndPosition = runtimeEventAndSetInMethodIdAndPosition;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        threadLocalDataWhenInTest.setExecuteAfterMethodCall(runtimeEventAndSetInMethodIdAndPosition);
        return TLinkableWrapper.emptyList();
    }
}
