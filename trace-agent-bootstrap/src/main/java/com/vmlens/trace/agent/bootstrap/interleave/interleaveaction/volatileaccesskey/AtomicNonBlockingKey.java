package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey;

import com.vmlens.trace.agent.bootstrap.interleave.loop.NormalizeContext;

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

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, VolatileKey other) {
        if(! (other instanceof AtomicNonBlockingKey)) {
            return false;
        }

        AtomicNonBlockingKey otherAtomicNonBlockingKey = (AtomicNonBlockingKey) other;

        int myCode = normalizeContext.normalizeObjectHashCode(objectHashCode);
        int otherCode =   normalizeContext.normalizeObjectHashCode(otherAtomicNonBlockingKey.objectHashCode);
        return myCode == otherCode;
    }
}
