package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.PreAnalyzedCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositorySingleton;


public class PreAnalyzedCallback {

    private static volatile PreAnalyzedCallbackImpl preAnalyzedCallbackImpl = new  PreAnalyzedCallbackImpl(
            MethodRepositorySingleton.INSTANCE,
            new ThreadLocalWhenInTestAdapterImpl(),
            ReadWriteLockMap.INSTANCE);

    public static void beforeMethodCall(int inMethodId, int position, int calledMethodId) {
        preAnalyzedCallbackImpl.beforeMethodCall(inMethodId, position, calledMethodId);
    }

    public static void beforeMethodCallInNonBlocking(Object object, int inMethodId, int position, int calledMethodId) {
        preAnalyzedCallbackImpl.beforeMethodCallInNonBlocking(object, inMethodId, position, calledMethodId);
    }

    public static void afterMethodCall(int inMethodId, int position, int calledMethodId) {
        preAnalyzedCallbackImpl.afterMethodCall(inMethodId, position, calledMethodId);
    }

    public static void methodEnter(Object object, int methodId) {
        preAnalyzedCallbackImpl.methodEnter(object, methodId);
    }

    public static void methodExit(Object object, int methodId) {
        preAnalyzedCallbackImpl.methodExit(object, methodId);
    }

    public static void methodExitObjectReturn(Object returnValue, Object object, int methodId) {
        preAnalyzedCallbackImpl.methodExitObjectReturn(returnValue, object, methodId);
    }

    public static void setPreAnalyzedCallbackImpl(PreAnalyzedCallbackImpl preAnalyzedCallbackImpl) {
        PreAnalyzedCallback.preAnalyzedCallbackImpl = preAnalyzedCallbackImpl;
    }
}
