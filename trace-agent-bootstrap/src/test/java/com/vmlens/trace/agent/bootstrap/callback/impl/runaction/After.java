package com.vmlens.trace.agent.bootstrap.callback.impl.runaction;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;

import java.util.Objects;

public class After implements RunAction {

    private final RuntimeEvent runtimeEvent;

    public After(RuntimeEvent runtimeEvent) {
        this.runtimeEvent = runtimeEvent;
    }

    public static After after(RuntimeEvent runtimeEvent) {
        return new After(runtimeEvent);
    }

    @Override
    public String toString() {
        return "After{" +
                "runtimeEvent=" + runtimeEvent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        After after = (After) o;

        return Objects.equals(runtimeEvent, after.runtimeEvent);
    }

    @Override
    public int hashCode() {
        return runtimeEvent != null ? runtimeEvent.hashCode() : 0;
    }
}
