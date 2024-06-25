package com.vmlens.trace.agent.bootstrap.callback.field;

/**
 * either volatile or non volatile
 */
public interface FieldAccess {
    Long field_access_from_generated_method(Object orig, Long offset, int
            fieldId, int methodId);
}
