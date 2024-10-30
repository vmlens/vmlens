package com.vmlens.report.element;

import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.MemoryAccessType;
import org.apache.commons.text.StringEscapeUtils;

public class FieldAccessTextFactory implements OperationTextFactory {

    private final String prefix;
    private final int fieldId;
    private final String postfix;

    public FieldAccessTextFactory(String prefix, int fieldId, String postfix) {
        this.prefix = prefix;
        this.fieldId = fieldId;
        this.postfix = postfix;
    }

    public static FieldAccessTextFactory create(MemoryAccessModifier memoryAccessModifier, int operation,
                                                int fieldId, long objectHashCode) {
        String prefix = memoryAccessModifier.postfix() + MemoryAccessType.asString(operation) + " " +
                memoryAccessModifier.volatilePrefix();
        return new FieldAccessTextFactory(prefix, fieldId,
                StringEscapeUtils.escapeHtml4("@") + objectHashCode);
    }

    @Override
    public String create() {
        return prefix + fieldId + postfix;
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        callback.needsField(fieldId);
    }
}
