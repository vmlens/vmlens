package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

public class ObjectHashCodeAndFieldId {

    private final long objectHashCode;
    private final int fieldId;

    public ObjectHashCodeAndFieldId(long objectHashCode, int fieldId) {
        this.objectHashCode = objectHashCode;
        this.fieldId = fieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectHashCodeAndFieldId that = (ObjectHashCodeAndFieldId) o;
        return objectHashCode == that.objectHashCode && fieldId == that.fieldId;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(objectHashCode);
        result = 31 * result + fieldId;
        return result;
    }
}
