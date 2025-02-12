package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionBeforeMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionMethodExit;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForCallback;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public class MethodCallbackImpl {

    private final MethodRepositoryForCallback methodIdToStrategy;
    private final OrderMap<Long> monitorOrder;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public MethodCallbackImpl(MethodRepositoryForCallback methodIdToStrategy,
                              OrderMap<Long> monitorOrder,
                              ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.methodIdToStrategy = methodIdToStrategy;
        this.monitorOrder = monitorOrder;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public void targetOfMethodCall(Object object, int calledMethodId) {
        // Fixme
    }

    public void beforeMethodCall(int calledMethodId) {
        threadLocalWhenInTestAdapter.process(new CallbackActionBeforeMethodCall());
    }

    public void afterMethodCall(int inMethodId, int position, int calledMethodId) {
        threadLocalWhenInTestAdapter.process(new CallbackActionAfterMethodCall(inMethodId, position));
    }

    public void methodEnter(Object object, int methodId) {
        methodIdToStrategy.strategyAll(methodId).onMethodEnter(object,
                methodId, monitorOrder, threadLocalWhenInTestAdapter);
    }


    public void methodExit(Object object, int methodId) {
        threadLocalWhenInTestAdapter.process(new CallbackActionMethodExit(methodId));
    }

    public void constructorMethodEnter(int methodId) {

    }
}
