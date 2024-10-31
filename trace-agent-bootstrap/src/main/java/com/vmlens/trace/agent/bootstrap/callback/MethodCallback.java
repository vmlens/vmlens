package com.vmlens.trace.agent.bootstrap.callback;

public class MethodCallback {

    public static void targetOfMethodCall(Object object, int methodId) {

    }

    // for atomic callback
    public static void argumentOfMethodCall(Object object, int position) {

    }

    public static void beforeMethodCall(int methodId) {

    }

    public static void afterMethodCall(int methodId) {

    }

    public static void methodEnter(Object object, int methodId) {

    }

    public static void staticMethodEnter(int methodId) {

    }

    public static void methodExit(int methodId) {

    }

}
