package com.vmlens.report.operationtextfactory;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.element.MemoryAccessModifier;
import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import org.apache.commons.text.StringEscapeUtils;

public class FieldAccessTextFactory implements OperationTextFactory {

    private final String text;
    private final int fieldId;

    public FieldAccessTextFactory(String text, int fieldId) {
        this.text = text;
        this.fieldId = fieldId;
    }

    public static FieldAccessTextFactory create(MemoryAccessModifier memoryAccessModifier, int operation,
                                                int fieldId, long objectHashCode) {
        String prefix = memoryAccessModifier.postfix() + MemoryAccessType.asString(operation) + " " +
                memoryAccessModifier.volatilePrefix();

        String text = prefix + " %s" + StringEscapeUtils.escapeHtml4("@") + objectHashCode;
        return new FieldAccessTextFactory(text, fieldId);
    }

    @Override
    public String create(DescriptionContext context) {
        return String.format(text, context.fieldName(fieldId));
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        callback.needsField(fieldId);
    }
}
