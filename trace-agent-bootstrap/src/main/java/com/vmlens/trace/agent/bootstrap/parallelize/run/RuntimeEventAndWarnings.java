package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.Warning;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper.wrap;

public class RuntimeEventAndWarnings {

    // runtimeEvent can be null
    private final RuntimeEvent runtimeEvent;
    private final TLinkedList<TLinkableWrapper<Warning>> warnings;

    public RuntimeEventAndWarnings(RuntimeEvent runtimeEvent, TLinkedList<TLinkableWrapper<Warning>> warnings) {
        this.runtimeEvent = runtimeEvent;
        this.warnings = warnings;
    }

    public static RuntimeEventAndWarnings empty() {
        return new RuntimeEventAndWarnings(null, new TLinkedList<>());
    }

    public static RuntimeEventAndWarnings of(RuntimeEvent runtimeEvent) {
        return new RuntimeEventAndWarnings(runtimeEvent, new TLinkedList<>());
    }

    public RuntimeEvent runtimeEvent() {
        return runtimeEvent;
    }

    public void addWarnings(TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents) {
        for (TLinkableWrapper<Warning> warning : warnings) {
            serializableEvents.add(wrap((SerializableEvent) warning.element()));
        }

    }
}
