package com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithInMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ExecuteRunEndAtomicAction<EVENT extends RuntimeEvent & WithInMethodIdAndPosition>
        implements ExecuteAfterMethodCall {

    private final EVENT event;

    public ExecuteRunEndAtomicAction(EVENT event) {
        this.event = event;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(int inMethodId, int position, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        event.setInMethodIdAndPosition(inMethodId, position);
        return threadLocalDataWhenInTest.runAdapter().endAtomicOperation(event,threadLocalDataWhenInTest);
    }
}
