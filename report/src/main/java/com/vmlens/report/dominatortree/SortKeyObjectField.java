package com.vmlens.report.dominatortree;

public class SortKeyObjectField  implements UIStateElementSortKey {

    private final int objectId;
    private final int fieldId;

    public SortKeyObjectField(int objectId, int fieldId) {
        this.objectId = objectId;
        this.fieldId = fieldId;
    }

    @Override
    public String idLabel() {
        return "(" +  objectId +  ")";
    }

}
