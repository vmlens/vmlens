package com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFields;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.WithInMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ExecuteRunAfter <EVENT extends RuntimeEvent & WithInMethodIdAndPosition>
        implements ExecuteAfterMethodCall {

    private final EVENT event;

    public ExecuteRunAfter(EVENT event) {
        this.event = event;

    }

    @Override
    public void execute(int inMethodId, int position,
                        ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        QueueIn queueIn,
                        ReadWriteLockMap readWriteLockMap) {
        event.setInMethodIdAndPosition(inMethodId, position, readWriteLockMap);
        threadLocalDataWhenInTest.runAdapter().after(event,threadLocalDataWhenInTest, queueIn);
    }
}
