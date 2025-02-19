package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class RunAdapter {

    private final RunForCallback run;

    public RunAdapter(RunForCallback run) {
        this.run = run;
    }

    public TLinkedList<TLinkableWrapper<SerializableEvent>> after(RuntimeEvent runtimeEventIn,
                                                                  ThreadLocalWhenInTest threadLocalWhenInTest) {
        runtimeEventIn.setThreadIndex(threadLocalWhenInTest.threadIndex());
        RuntimeEventAndWarnings runtimeEventAndWarning = run.after(runtimeEventIn, threadLocalWhenInTest);
        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents = emptyList();

        if (runtimeEventAndWarning.runtimeEvent() != null) {
            runtimeEventAndWarning.runtimeEvent().setMethodCounter(threadLocalWhenInTest);
            serializableEvents.add(wrap(runtimeEventAndWarning.runtimeEvent()));
        }
        runtimeEventAndWarning.addWarnings(serializableEvents);
        return serializableEvents;
    }

    public TLinkedList<TLinkableWrapper<SerializableEvent>> endAtomicOperation(RuntimeEvent runtimeEventIn,
                                                                               ThreadLocalWhenInTest threadLocalWhenInTest) {
        runtimeEventIn.setThreadIndex(threadLocalWhenInTest.threadIndex());
        RuntimeEventAndWarnings runtimeEventAndWarning = run.endAtomicAction(runtimeEventIn, threadLocalWhenInTest);
        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents = emptyList();

        if (runtimeEventAndWarning.runtimeEvent() != null) {
            runtimeEventAndWarning.runtimeEvent().setMethodCounter(threadLocalWhenInTest);
            serializableEvents.add(wrap(runtimeEventAndWarning.runtimeEvent()));
        }
        runtimeEventAndWarning.addWarnings(serializableEvents);
        return serializableEvents;
    }

    public void startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                                  RunnableOrThreadWrapper newThread) {
        run.startAtomicActionWithNewThread(threadLocalDataWhenInTest, newThread);
    }

    public void startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        run.startAtomicAction(threadLocalDataWhenInTest);
    }

}
