package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey;

import com.vmlens.trace.agent.bootstrap.interleave.loop.NormalizeContext;

public class VolatileArrayKey  implements VolatileKey {

    private final int index;
    private final long objectHashCode;

    public VolatileArrayKey(int index, long objectHashCode) {
        this.index = index;
        this.objectHashCode = objectHashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        VolatileArrayKey that = (VolatileArrayKey) object;
        return index == that.index && objectHashCode == that.objectHashCode;
    }

    @Override
    public int hashCode() {
        int result = index;
        result = 31 * result + Long.hashCode(objectHashCode);
        return result;
    }

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, VolatileKey other) {
        if(! (other instanceof VolatileArrayKey)) {
            return false;
        }

        VolatileArrayKey otherAtomicNonBlockingKey = (VolatileArrayKey) other;

        int myCode = normalizeContext.normalizeObjectHashCode(objectHashCode);
        int otherCode =   normalizeContext.normalizeObjectHashCode(otherAtomicNonBlockingKey.objectHashCode);
        return myCode == otherCode && index == otherAtomicNonBlockingKey.index;
    }
}
