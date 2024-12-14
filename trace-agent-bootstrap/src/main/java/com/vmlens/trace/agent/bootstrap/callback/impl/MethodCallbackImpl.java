package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodIdToStrategy;

public class MethodCallbackImpl {

    private final MethodIdToStrategy methodIdToStrategy;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public MethodCallbackImpl(MethodIdToStrategy methodIdToStrategy,
                              ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.methodIdToStrategy = methodIdToStrategy;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public void targetOfMethodCall(Object object, int calledMethodId) {
        methodIdToStrategy.beforeMethodCallStrategy(calledMethodId);
    }

    public void beforeMethodCall(int calledMethodId) {

    }

    public void afterMethodCall(int inMethodId, int position, int calledMethodId) {

    }

    public void methodEnter(Object object, int methodId) {

    }

    public void staticMethodEnter(int methodId) {

    }

    public void methodExit(int methodId) {

    }

    public void constructorMethodEnter(int methodId) {

    }
}
