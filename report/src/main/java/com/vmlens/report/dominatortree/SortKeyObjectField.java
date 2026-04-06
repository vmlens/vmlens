package com.vmlens.report.dominatortree;

import java.util.Objects;

public class SortKeyObjectField  implements UIStateElementSortKey {

    private final int objectId;

    public SortKeyObjectField(int objectId) {
        this.objectId = objectId;
    }

    @Override
    public String idLabel() {
        return "(" +  objectId +  ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SortKeyObjectField that = (SortKeyObjectField) o;
        return objectId == that.objectId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(objectId);
    }
}
