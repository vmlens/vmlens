package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.impl.MethodCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.CheckIsThreadRun;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.*;

public class MethodCallback {

    private static volatile MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl(
            ParallelizeFacade.parallelize(),
            MethodRepositorySingleton.INSTANCE,
            new ThreadLocalWhenInTestAdapterImpl(),
            new CheckIsThreadRun(),
            ReadWriteLockMap.INSTANCE);

    public static void beforeMethodCall(int inMethodId, int position, int calledMethodId) {
        if(canProcess()) {
            startProcess();
            try {
                // calledMethodId is only used in PreAnalyzedCallback
                methodCallbackImpl.beforeMethodCall(inMethodId, position);
        } finally {
            stopProcess();
        }
    }
}

    public static void afterMethodCall(int inMethodId, int position, int calledMethodId) {
        if(canProcess()) {
            startProcess();
            try {
                // calledMethodId is only used in PreAnalyzedCallback
                methodCallbackImpl.afterMethodCall(inMethodId, position);
            } finally {
                stopProcess();
            }
        }
    }

    public static void methodEnter(Object object, int methodId) {
        if(canProcess()) {
            startProcess();
            try {
                methodCallbackImpl.methodEnter(object, methodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void methodExit(Object object, int methodId) {
        if(canProcess()) {
            startProcess();
            try {
                methodCallbackImpl.methodExit(object, methodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void constructorMethodEnter(int methodId) {
        if(canProcess()) {
            startProcess();
            try {
                methodCallbackImpl.constructorMethodEnter(methodId);
            } finally {
                stopProcess();
            }
        }
    }

    public static void constructorMethodExit(int methodId) {
        if(canProcess()) {
            startProcess();
            try {
                methodCallbackImpl.constructorMethodExit(methodId);
            } finally {
                stopProcess();
            }
        }
    }

    // Visible for Test
    public static void setMethodCallbackImpl(MethodCallbackImpl methodCallbackImpl) {
        MethodCallback.methodCallbackImpl = methodCallbackImpl;
    }
}
