package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionBeforeMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionMethodExit;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodIdToStrategy;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public class MethodCallbackImpl {

    private final MethodIdToStrategy methodIdToStrategy;
    private final OrderMap<Long> monitorOrder;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public MethodCallbackImpl(MethodIdToStrategy methodIdToStrategy,
                              OrderMap<Long> monitorOrder,
                              ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.methodIdToStrategy = methodIdToStrategy;
        this.monitorOrder = monitorOrder;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public void targetOfMethodCall(Object object, int calledMethodId) {
        methodIdToStrategy
                .beforeMethodCallStrategy(calledMethodId)
                .targetOfMethodCall(object, calledMethodId, threadLocalWhenInTestAdapter);
    }

    public void beforeMethodCall(int calledMethodId) {
        threadLocalWhenInTestAdapter.process(new CallbackActionBeforeMethodCall());
    }

    public void afterMethodCall(int inMethodId, int position, int calledMethodId) {
        threadLocalWhenInTestAdapter.process(new CallbackActionAfterMethodCall(inMethodId, position));
    }

    public void methodEnter(Object object, int methodId) {
        methodIdToStrategy.methodEnterStrategy(methodId).onMethodEnter(object,
                methodId, monitorOrder, threadLocalWhenInTestAdapter);
    }


    public void methodExit(Object object, int methodId) {
        threadLocalWhenInTestAdapter.process(new CallbackActionMethodExit(methodId));
    }

    public void constructorMethodEnter(int methodId) {

    }
}
