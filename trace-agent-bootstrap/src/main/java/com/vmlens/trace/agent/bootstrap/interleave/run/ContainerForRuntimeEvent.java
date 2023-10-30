package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;

import java.util.Objects;

public class ContainerForRuntimeEvent implements InterleaveActionWithPositionFactoryAndOrRuntimeEvent {
    private final RuntimeEvent runtimeEvent;

    public ContainerForRuntimeEvent(RuntimeEvent runtimeEvent) {
        this.runtimeEvent = runtimeEvent;
    }

    @Override
    public void apply(ActualRun actualRun, ActualRunContext actualRunContext) {
        actualRunContext.setRunIdsInRuntimeEvent(runtimeEvent);
        runtimeEvent.send(actualRunContext.sendEventContext());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerForRuntimeEvent that = (ContainerForRuntimeEvent) o;

        return Objects.equals(runtimeEvent, that.runtimeEvent);
    }

    @Override
    public int hashCode() {
        return runtimeEvent != null ? runtimeEvent.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ContainerForRuntimeEvent{" +
                "runtimeEvent=" + runtimeEvent +
                '}';
    }
}
