package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.MethodCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMapSingleton;

public class MethodCallback {

    private static volatile MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl(
            MethodRepositorySingleton.INSTANCE,
            OrderMapSingleton.MONITOR_ORDER,
            new ThreadLocalWhenInTestAdapterImpl());

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


    public static void constructorMethodEnter(int methodId) {
        methodCallbackImpl.constructorMethodEnter(methodId);
    }

    public static void methodExit(Object object, int methodId) {
        methodCallbackImpl.methodExit(object, methodId);
    }

    // Visible for Test
    public static void setMethodCallbackImpl(MethodCallbackImpl methodCallbackImpl) {
        MethodCallback.methodCallbackImpl = methodCallbackImpl;
    }
}
