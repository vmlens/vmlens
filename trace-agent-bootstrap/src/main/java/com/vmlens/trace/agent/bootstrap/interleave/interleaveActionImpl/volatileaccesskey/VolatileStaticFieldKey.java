package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.volatileaccesskey;

public class VolatileStaticFieldKey implements VolatileKey  {

    private final int fieldId;

    public VolatileStaticFieldKey(int fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        VolatileStaticFieldKey that = (VolatileStaticFieldKey) o;
        return fieldId == that.fieldId;
    }

    @Override
    public int hashCode() {
        return fieldId;
    }
}
