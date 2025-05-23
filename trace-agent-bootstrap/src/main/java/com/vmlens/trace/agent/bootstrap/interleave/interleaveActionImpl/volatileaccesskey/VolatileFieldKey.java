package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.volatileaccesskey;


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
}
