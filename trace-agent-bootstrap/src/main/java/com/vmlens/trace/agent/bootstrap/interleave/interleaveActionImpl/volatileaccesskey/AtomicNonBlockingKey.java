package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.volatileaccesskey;

public class AtomicNonBlockingKey  implements VolatileKey  {

    private final long objectHashCode;

    public AtomicNonBlockingKey(long objectHashCode) {
        this.objectHashCode = objectHashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        AtomicNonBlockingKey that = (AtomicNonBlockingKey) object;
        return objectHashCode == that.objectHashCode;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(objectHashCode);
    }
}
