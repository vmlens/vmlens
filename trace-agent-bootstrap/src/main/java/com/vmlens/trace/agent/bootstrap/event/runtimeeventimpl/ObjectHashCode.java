package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

public class ObjectHashCode {

    private final long objectHashCode;

    public ObjectHashCode(long objectHashCode) {
        this.objectHashCode = objectHashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectHashCode that = (ObjectHashCode) o;

        return objectHashCode == that.objectHashCode;
    }

    @Override
    public int hashCode() {
        return (int) (objectHashCode ^ (objectHashCode >>> 32));
    }
}
