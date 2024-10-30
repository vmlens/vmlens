package com.vmlens.trace.agent.bootstrap.callback;

public class MethodCallback {

    public static void targetOfMethodCall(Object object) {

    }

    // for atomic callback
    public static void argumentOfMethodCall(Object object, int position) {

    }

    public static void beforeMethodCall(int methodId) {

    }

    public static void afterMethodCall() {

    }

    public static void methodEnter(Object object, int methodId) {

    }

    public static void staticMethodEnter(int methodId) {

    }

    public static void methodExit(int methodId) {

    }

}
