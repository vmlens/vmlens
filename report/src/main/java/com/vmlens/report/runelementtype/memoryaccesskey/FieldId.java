package com.vmlens.report.runelementtype.memoryaccesskey;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;

public class FieldId implements MemoryAccessKey {

    private final int fieldId;

    public FieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public String asString(DescriptionContext context) {
        return "static " + context.fieldName(fieldId);
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        callback.needsField(fieldId);
    }

}
