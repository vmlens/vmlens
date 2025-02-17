package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionBeforeMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionMethodExit;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForCallback;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.CheckIsThreadRun;

public class MethodCallbackImpl {

    private final ParallelizeFacade parallelizeFacade;
    private final MethodRepositoryForCallback methodIdToStrategy;
    private final OrderMap<Long> monitorOrder;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final CheckIsThreadRun checkIsThreadRun;

    public MethodCallbackImpl(ParallelizeFacade parallelizeFacade,
                              MethodRepositoryForCallback methodIdToStrategy,
                              OrderMap<Long> monitorOrder,
                              ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                              CheckIsThreadRun checkIsThreadRun) {
        this.parallelizeFacade = parallelizeFacade;
        this.methodIdToStrategy = methodIdToStrategy;
        this.monitorOrder = monitorOrder;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        this.checkIsThreadRun = checkIsThreadRun;
    }

    public void beforeMethodCall(int calledMethodId) {
        threadLocalWhenInTestAdapter.process(new CallbackActionBeforeMethodCall());
    }

    public void afterMethodCall(int inMethodId, int position, int calledMethodId) {
        threadLocalWhenInTestAdapter.process(new CallbackActionAfterMethodCall(inMethodId, position));
    }

    public void methodEnter(Object object, int methodId) {
        methodIdToStrategy.strategyAll(methodId).onMethodEnter(object,
                methodId, monitorOrder, threadLocalWhenInTestAdapter, checkIsThreadRun, parallelizeFacade);
    }


    public void methodExit(Object object, int methodId) {
        threadLocalWhenInTestAdapter.process(new CallbackActionMethodExit(methodId));
    }

}
