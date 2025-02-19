package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class RunStartAtomicActionWithNewThread implements CallbackAction {

    private final RunnableOrThreadWrapper newThread;

    public RunStartAtomicActionWithNewThread(RunnableOrThreadWrapper newThread) {
        this.newThread = newThread;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        threadLocalDataWhenInTest.runAdapter().startAtomicOperationWithNewThread(threadLocalDataWhenInTest, newThread);
        return TLinkableWrapper.emptyList();
    }
}
