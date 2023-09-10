package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.method.MethodCallbackImpl;


public class MethodCallback {

    private static volatile MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl();

    public static void atomicMethodEnterWithCallback(int atomicId, int methodId) {
        methodCallbackImpl.atomicMethodEnterWithCallback(atomicId, methodId);
    }

    public static void atomicMethodEnterWithoutCallback(int atomicId, int methodId) {
        methodCallbackImpl.atomicMethodEnterWithoutCallback(atomicId, methodId);
    }

    public static void atomicMethodExitWithCallback(int atomicId, int methodId) {
        methodCallbackImpl.atomicMethodExitWithCallback(atomicId, methodId);
    }

    public static void atomicMethodExitWithoutCallback(int atomicId, int methodId) {
        methodCallbackImpl.atomicMethodExitWithoutCallback(atomicId, methodId);
    }

    public static void methodEnterThreadRun(int methodId) {
        methodCallbackImpl.methodEnterThreadRun(methodId);
    }

    public static void methodExitThreadRun(int methodId) {
        methodCallbackImpl.methodExitThreadRun(methodId);
    }

    public static void methodEnter(int methodId) {
        methodCallbackImpl.methodEnter(methodId);
    }

    public static void methodExit(int methodId) {
        methodCallbackImpl.methodExit(methodId);
    }

    public static void taskMethodEnter() {
        methodCallbackImpl.taskMethodEnter();
    }

    public static void taskMethodExit() {
        methodCallbackImpl.taskMethodExit();
    }

    public static void semaphoreAcquireExit() {
        methodCallbackImpl.semaphoreAcquireExit();
    }

    // For Test
    public static void setMethodCallbackImpl(MethodCallbackImpl methodCallbackImpl) {
        MethodCallback.methodCallbackImpl = methodCallbackImpl;
    }
}
