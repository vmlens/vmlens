package com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public interface ExecuteAfterMethodCall {

    void execute(int inMethodId, int position,
                 ThreadLocalWhenInTest threadLocalDataWhenInTest,
                 QueueIn queueIn,
                 ReadWriteLockMap readWriteLockMap);

}
