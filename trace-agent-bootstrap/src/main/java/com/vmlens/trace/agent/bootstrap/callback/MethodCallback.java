package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.MethodCallbackImpl;

public class MethodCallback {

    private static volatile MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl();

    public static void targetOfMethodCall(Object object, int calledMethodId) {
        methodCallbackImpl.targetOfMethodCall(object, calledMethodId);
    }

    public static void beforeMethodCall(int calledMethodId) {
        methodCallbackImpl.beforeMethodCall(calledMethodId);
    }

    public static void afterMethodCall(int inMethodId, int position, int calledMethodId) {
        methodCallbackImpl.afterMethodCall(inMethodId, position, calledMethodId);
    }

    public static void methodEnter(Object object, int methodId) {
        methodCallbackImpl.methodEnter(object, methodId);
    }

    public static void staticMethodEnter(int methodId) {
        methodCallbackImpl.staticMethodEnter(methodId);
    }

    public static void methodExit(int methodId) {
        methodCallbackImpl.methodExit(methodId);
    }

    // Visible for Test
    public static void setMethodCallbackImpl(MethodCallbackImpl methodCallbackImpl) {
        MethodCallback.methodCallbackImpl = methodCallbackImpl;
    }
}
