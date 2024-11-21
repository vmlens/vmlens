package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.FieldCallbackImpl;

public class FieldCallback {

    private static volatile FieldCallbackImpl fieldCallbackImpl = new FieldCallbackImpl(null, null, null);

    public static void beforeFieldRead(Object fromObject, int fieldId, int position, int inMethodId) {
        fieldCallbackImpl.beforeFieldRead(fromObject, fieldId, position, inMethodId);
    }

    public static void beforeFieldWrite(Object fromObject, int fieldId, int position, int inMethodId) {
        fieldCallbackImpl.beforeFieldWrite(fromObject, fieldId, position, inMethodId);
    }

    public static void beforeStaticFieldRead(int fieldId, int position, int inMethodId) {
        fieldCallbackImpl.beforeStaticFieldRead(fieldId, position, inMethodId);
    }

    public static void beforeStaticFieldWrite(int fieldId, int position, int inMethodId) {
        fieldCallbackImpl.beforeStaticFieldWrite(fieldId, position, inMethodId);
    }

    public static void afterFieldAccess() {
        fieldCallbackImpl.afterFieldAccess();
    }

    public static void setFieldCallbackImpl(FieldCallbackImpl fieldCallbackImpl) {
        FieldCallback.fieldCallbackImpl = fieldCallbackImpl;
    }
}
