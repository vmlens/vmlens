package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class RunStartAtomicActionWithNewThread implements CallbackAction {

    private final RunnableOrThreadWrapper newThread;

    public RunStartAtomicActionWithNewThread(RunnableOrThreadWrapper newThread) {
        this.newThread = newThread;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        threadLocalDataWhenInTest.runAdapter().startAtomicOperationWithNewThread(threadLocalDataWhenInTest, newThread, queueIn);
    }
}
