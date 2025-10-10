package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey;

import java.util.Objects;

public class VolatileFieldKey implements VolatileKey {

    private final int fieldId;
    private final long objectHashCode;

    public VolatileFieldKey(int fieldId, long objectHashCode) {
        this.fieldId = fieldId;
        this.objectHashCode = objectHashCode;
    }

    @Override
    public boolean equalsNormalized(VolatileKey other) {
        if(! (other instanceof VolatileFieldKey)) {
            return false;
        }
        return fieldId == ((VolatileFieldKey) other).fieldId;
    }

    @Override
    public String toString() {
        return "volatileField(" + fieldId + ","+ objectHashCode + "L)";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        VolatileFieldKey that = (VolatileFieldKey) object;
        return fieldId == that.fieldId && objectHashCode == that.objectHashCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(),fieldId, objectHashCode);
    }
}
