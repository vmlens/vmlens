package com.vmlens.trace.agent.bootstrap.callback.impl.runaction;

public class StartAtomicOperation implements RunAction {

    public static StartAtomicOperation startAtomicOperation() {
        return new StartAtomicOperation();
    }

    @Override
    public String toString() {
        return "StartAtomicOperation{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 12;
    }

}
