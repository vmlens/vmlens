package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.warning.Warning;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

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

    public static RuntimeEventAndWarnings of(RuntimeEvent runtimeEvent, Warning warning) {
        TLinkedList<TLinkableWrapper<Warning>> warnings = new TLinkedList<>();
        warnings.add(wrap(warning));
        return new RuntimeEventAndWarnings(runtimeEvent, warnings);
    }

    public RuntimeEvent runtimeEvent() {
        return runtimeEvent;
    }

    public void addWarnings(TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents) {
        for (TLinkableWrapper<Warning> warning : warnings) {
            serializableEvents.add(wrap(warning.element()));
        }

    }
}
