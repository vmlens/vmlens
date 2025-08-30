package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.OnAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetInMethodIdAndPositionAtThreadLocal;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.InMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.CheckIsThreadRun;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.MethodEnterExitContext;

public class MethodCallbackImpl {

    private final ParallelizeFacade parallelizeFacade;
    private final MethodRepositoryForCallback methodIdToStrategy;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final CheckIsThreadRun checkIsThreadRun;
    private final ReadWriteLockMap readWriteLockMap;

    public MethodCallbackImpl(ParallelizeFacade parallelizeFacade,
                              MethodRepositoryForCallback methodIdToStrategy,
                              ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                              CheckIsThreadRun checkIsThreadRun, ReadWriteLockMap readWriteLockMap) {
        this.parallelizeFacade = parallelizeFacade;
        this.methodIdToStrategy = methodIdToStrategy;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        this.checkIsThreadRun = checkIsThreadRun;
        this.readWriteLockMap = readWriteLockMap;
    }

    public void beforeMethodCall(int inMethodId, int position) {
        threadLocalWhenInTestAdapter.process(new SetInMethodIdAndPositionAtThreadLocal(
                new InMethodIdAndPosition(inMethodId, position)));
    }

    public void afterMethodCall(int inMethodId, int position) {
        threadLocalWhenInTestAdapter.process(new OnAfterMethodCall(inMethodId, position,readWriteLockMap));
    }

    public void methodEnter(Object object, int methodId) {
        MethodEnterExitContext enterExitContext = new MethodEnterExitContext(object,
                methodId,
                threadLocalWhenInTestAdapter,
                checkIsThreadRun,
                parallelizeFacade);
        methodIdToStrategy.strategyAll(methodId).methodEnter(enterExitContext);
    }

    public void methodExit(Object object, int methodId) {
        MethodEnterExitContext enterExitContext = new MethodEnterExitContext(object,
                methodId,
                threadLocalWhenInTestAdapter,
                checkIsThreadRun,
                parallelizeFacade);
        methodIdToStrategy.strategyAll(methodId).methodExit(enterExitContext);
    }

    public void constructorMethodEnter(int methodId) {

    }

    public void constructorMethodExit(int methodId) {

    }
}
