package com.vmlens.report.runelementtype.memoryaccesskey;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;

public class FieldIdAndObjectHashcode implements MemoryAccessKey {

    private final int fieldId;
    private final long objectHashCode;

    public FieldIdAndObjectHashcode(int fieldId, long objectHashCode) {
        this.fieldId = fieldId;
        this.objectHashCode = objectHashCode;
    }

    @Override
    public String asString(DescriptionContext context) {
        return context.fieldName(fieldId) ;
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        callback.needsField(fieldId);
    }

    @Override
    public String objectHashCode() {
        return ""  + objectHashCode;
    }
}
