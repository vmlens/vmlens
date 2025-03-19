package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.volatileaccesskey;


public class VolatileFieldAccessKey implements VolatileAccessKey  {

    private final int fieldId;
    private final long objectHashCode;

    public VolatileFieldAccessKey(int fieldId, long objectHashCode) {
        this.fieldId = fieldId;
        this.objectHashCode = objectHashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        VolatileFieldAccessKey that = (VolatileFieldAccessKey) o;
        return fieldId == that.fieldId && objectHashCode == that.objectHashCode;
    }

    @Override
    public int hashCode() {
        int result = fieldId;
        result = 31 * result + Long.hashCode(objectHashCode);
        return result;
    }
}
