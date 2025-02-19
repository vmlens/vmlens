package com.vmlens.trace.agent.bootstrap.callback.impl.runaction;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;

import java.util.Objects;

public class EndAtomicOperation implements RunAction {

    private final RuntimeEvent runtimeEvent;

    public EndAtomicOperation(RuntimeEvent runtimeEvent) {
        this.runtimeEvent = runtimeEvent;
    }

    public static EndAtomicOperation endAtomicOperation(RuntimeEvent runtimeEvent) {
        return new EndAtomicOperation(runtimeEvent);
    }

    @Override
    public String toString() {
        return "EndAtomicOperation{" +
                "runtimeEvent=" + runtimeEvent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EndAtomicOperation that = (EndAtomicOperation) o;

        return Objects.equals(runtimeEvent, that.runtimeEvent);
    }

    @Override
    public int hashCode() {
        return runtimeEvent != null ? runtimeEvent.hashCode() : 0;
    }
}
