package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;


public class VolatileFieldAccessKey  {
    private final int fieldId;

    public VolatileFieldAccessKey(int fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolatileFieldAccessKey that = (VolatileFieldAccessKey) o;

        return fieldId == that.fieldId;
    }

    @Override
    public int hashCode() {
        return fieldId;
    }
}
