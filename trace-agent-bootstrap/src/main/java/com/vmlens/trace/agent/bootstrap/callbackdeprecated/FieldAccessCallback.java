package com.vmlens.trace.agent.bootstrap.callbackdeprecated;


import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.FieldAccessCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.GetOrCreateObjectState;


public class FieldAccessCallback {

    private static volatile FieldAccessCallbackImpl fieldAccessCallbackImpl = new FieldAccessCallbackImpl(
            new GetOrCreateObjectState(),
            new ThreadLocalWhenInTestAdapterImpl());

    public static void field_access_static(int fieldId, int methodId, int callbackId) {
        fieldAccessCallbackImpl.field_access_static(fieldId, methodId, callbackId);
    }


    public static Long field_access_from_generated_method(Object orig, Long offset, int fieldId, int methodId, int callbackId) {
        return fieldAccessCallbackImpl.field_access_from_generated_method(orig, offset, fieldId, methodId, callbackId);
    }


    public static void field_access(Object orig, int fieldId, int methodId, int callbackId) {
        fieldAccessCallbackImpl.field_access(orig, fieldId, methodId, callbackId);
    }

    // For Test
    public static void setFieldAccessCallbackImpl(FieldAccessCallbackImpl fieldAccessCallbackImpl) {
        FieldAccessCallback.fieldAccessCallbackImpl = fieldAccessCallbackImpl;
    }
}
