package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class RunAdapter {

    private final Run run;

    public RunAdapter(Run run) {
        this.run = run;
    }

    public TLinkedList<TLinkableWrapper<SerializableEvent>> after(RuntimeEvent runtimeEventIn,
                                                                  ThreadLocalWhenInTest threadLocalWhenInTest) {
        runtimeEventIn.setThreadIndex(threadLocalWhenInTest.threadIndex());
        RuntimeEventAndWarnings runtimeEventAndWarning = run.after(runtimeEventIn, threadLocalWhenInTest);
        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents = emptyList();

        if (runtimeEventAndWarning.runtimeEvent() != null) {
            runtimeEventAndWarning.runtimeEvent().setMethodCounter(threadLocalWhenInTest);
            serializableEvents.add(wrap((SerializableEvent) runtimeEventAndWarning.runtimeEvent()));
        }
        runtimeEventAndWarning.addWarnings(serializableEvents);
        return serializableEvents;
    }
}
