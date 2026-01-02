package com.vmlens.report.dominatortree;

public class SortKeyStaticField implements UIStateElementSortKey {

    private final int fieldId;

    public SortKeyStaticField(int fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public String idLabel() {
        return "";
    }
}
