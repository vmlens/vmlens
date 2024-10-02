package com.vmlens.report.element;

public class FieldAccessTextFactory implements OperationTextFactory {

    private final String prefix;
    private final int fieldId;

    public FieldAccessTextFactory(String prefix, int fieldId) {
        this.prefix = prefix;
        this.fieldId = fieldId;
    }

    @Override
    public String create() {
        return prefix + fieldId;
    }
}
