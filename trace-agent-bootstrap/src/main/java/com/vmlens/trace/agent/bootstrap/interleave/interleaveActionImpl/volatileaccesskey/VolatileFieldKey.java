package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.volatileaccesskey;


import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public class VolatileFieldKey implements VolatileKey {

    private final int fieldId;
    private final long objectHashCode;

    public VolatileFieldKey(int fieldId, long objectHashCode) {
        this.fieldId = fieldId;
        this.objectHashCode = objectHashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        VolatileFieldKey that = (VolatileFieldKey) o;
        return fieldId == that.fieldId && objectHashCode == that.objectHashCode;
    }

    @Override
    public int hashCode() {
        int result = fieldId;
        result = 31 * result + Long.hashCode(objectHashCode);
        return result;
    }

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, VolatileKey other) {
        if(! (other instanceof VolatileFieldKey)) {
            return false;
        }

        VolatileFieldKey otherAtomicNonBlockingKey = (VolatileFieldKey) other;

        int myCode = normalizeContext.normalizeObjectHashCode(objectHashCode);
        int otherCode =   normalizeContext.normalizeObjectHashCode(otherAtomicNonBlockingKey.objectHashCode);
        return myCode == otherCode && fieldId == otherAtomicNonBlockingKey.fieldId;
    }
}
