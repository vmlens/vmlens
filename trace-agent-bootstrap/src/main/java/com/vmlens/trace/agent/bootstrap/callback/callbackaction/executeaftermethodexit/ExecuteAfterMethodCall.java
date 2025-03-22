package com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public interface ExecuteAfterMethodCall {

    TLinkedList<TLinkableWrapper<SerializableEvent>> execute(int inMethodId,
                                                             int position,
                                                             ThreadLocalWhenInTest threadLocalDataWhenInTest);

}
