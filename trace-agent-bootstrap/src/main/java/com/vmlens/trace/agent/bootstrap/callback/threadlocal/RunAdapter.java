package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class RunAdapter {

    private final RunForCallback run;

    public RunAdapter(RunForCallback run) {
        this.run = run;
    }

    public void after(RuntimeEvent runtimeEventIn,
                      ThreadLocalWhenInTest threadLocalWhenInTest,
                      QueueIn queueIn) {
        runtimeEventIn.setThreadIndex(threadLocalWhenInTest.threadIndex());
        TLinkedList<TLinkableWrapper<SerializableEvent>> runtimeEventAndWarning = run.after(runtimeEventIn, threadLocalWhenInTest);
        queueIn.offer(runtimeEventAndWarning);

    }

    public void endAtomicOperation(RuntimeEvent runtimeEventIn,
                                   ThreadLocalWhenInTest threadLocalWhenInTest,
                                   QueueIn queueIn) {
        runtimeEventIn.setThreadIndex(threadLocalWhenInTest.threadIndex());
        TLinkedList<TLinkableWrapper<SerializableEvent>> runtimeEventAndWarning = run.endAtomicAction(runtimeEventIn, threadLocalWhenInTest);
        queueIn.offer(runtimeEventAndWarning);
    }

    public void startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                                  RunnableOrThreadWrapper newThread,
                                                  QueueIn queueIn) {
        queueIn.offer(run.startAtomicActionWithNewThread(threadLocalDataWhenInTest, newThread));
    }

    public void startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                     QueueIn queueIn) {
        queueIn.offer(run.startAtomicAction(threadLocalDataWhenInTest));
    }

}
