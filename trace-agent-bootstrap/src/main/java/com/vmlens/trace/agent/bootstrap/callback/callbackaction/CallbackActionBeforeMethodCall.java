package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper.emptyList;

public class CallbackActionBeforeMethodCall implements CallbackAction {

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        ThreadStartEvent threadStartEvent = threadLocalDataWhenInTest.threadStartEvent();
        if (threadStartEvent != null) {
            threadLocalDataWhenInTest
                    .runAdapter()
                    .startAtomicOperationWithNewThread(threadLocalDataWhenInTest,
                            threadStartEvent.runnableOrThreadWrapper());
            threadStartEvent.setRunnableOrThreadWrapperToNull();
        }
        return emptyList();
    }
}
