package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;

public class OnAfterMethodCall implements CallbackAction {

    private final int inMethodId;
    private final int position;
    private final ReadWriteLockMap readWriteLockMap;

    public OnAfterMethodCall(int inMethodId,
                             int position,
                             ReadWriteLockMap readWriteLockMap) {
        this.inMethodId = inMethodId;
        this.position = position;
        this.readWriteLockMap = readWriteLockMap;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        if (threadLocalDataWhenInTest.executeAfterMethodCall() == null) {
            return;
        }

        threadLocalDataWhenInTest.executeAfterMethodCall().execute(inMethodId, position, threadLocalDataWhenInTest,queueIn,
                readWriteLockMap);
        threadLocalDataWhenInTest.setExecuteAfterMethodCall(null);

    }
}
