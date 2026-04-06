package com.vmlens.report.dominatortree;

import java.util.Objects;

public class SortKeyAtomicObject implements UIStateElementSortKey {

    private final int objectId;

    public SortKeyAtomicObject(int objectId) {
        this.objectId = objectId;
    }

    @Override
    public String idLabel() {
        return "(" +  objectId +  ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SortKeyAtomicObject that = (SortKeyAtomicObject) o;
        return objectId == that.objectId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(objectId);
    }
}
