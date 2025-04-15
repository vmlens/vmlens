package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.PreAnalyzedCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositorySingleton;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.*;


public class PreAnalyzedCallback {

    private static volatile PreAnalyzedCallbackImpl preAnalyzedCallbackImpl = new  PreAnalyzedCallbackImpl(
            MethodRepositorySingleton.INSTANCE,
            new ThreadLocalWhenInTestAdapterImpl(),
            ReadWriteLockMap.INSTANCE);

    public static void beforeMethodCall(int inMethodId, int position, int calledMethodId) {
        if(canProcess()) {
            startProcess();
            try {
                preAnalyzedCallbackImpl.beforeMethodCall(inMethodId, position, calledMethodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void beforeMethodCallInNonBlocking(Object object, int inMethodId, int position, int calledMethodId) {
        if(canProcess()) {
            startProcess();
            try {
                preAnalyzedCallbackImpl.beforeMethodCallInNonBlocking(object, inMethodId, position, calledMethodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void afterMethodCall(int inMethodId, int position, int calledMethodId) {
        if(canProcess()) {
            startProcess();
            try {
                preAnalyzedCallbackImpl.afterMethodCall(inMethodId, position, calledMethodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void methodEnter(Object object, int methodId) {
        if(canProcess()) {
            startProcess();
            try {
                preAnalyzedCallbackImpl.methodEnter(object, methodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void methodExit(Object object, int methodId) {
        if(canProcess()) {
            startProcess();
            try {
                preAnalyzedCallbackImpl.methodExit(object, methodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void methodExitObjectReturn(Object returnValue, Object object, int methodId) {
        if(canProcess()) {
            startProcess();
            try {
                preAnalyzedCallbackImpl.methodExitObjectReturn(returnValue, object, methodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void setPreAnalyzedCallbackImpl(PreAnalyzedCallbackImpl preAnalyzedCallbackImpl) {
        PreAnalyzedCallback.preAnalyzedCallbackImpl = preAnalyzedCallbackImpl;
    }
}
