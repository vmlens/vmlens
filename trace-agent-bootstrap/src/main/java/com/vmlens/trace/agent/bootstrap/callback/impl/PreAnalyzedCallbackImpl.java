package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.BeforeAfterContext;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.EnterExitContext;

public class PreAnalyzedCallbackImpl {

    private final MethodRepositoryForCallback methodRepository;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final ReadWriteLockMap readWriteLockMap;
    private final ParallelizeFacade parallelizeFacade;

    public PreAnalyzedCallbackImpl(MethodRepositoryForCallback methodRepository,
                                   ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                                   ReadWriteLockMap readWriteLockMap,
                                   ParallelizeFacade parallelizeFacade) {
        this.methodRepository = methodRepository;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        this.readWriteLockMap = readWriteLockMap;
        this.parallelizeFacade = parallelizeFacade;
    }

    public void beforeMethodCall(int inMethodId, int position, int calledMethodId) {
        BeforeAfterContext beforeAfterContext = new BeforeAfterContext();
        methodRepository.strategyPreAnalyzed(inMethodId).beforeMethodCall(beforeAfterContext);
    }

    public void beforeMethodCallInNonBlocking(Object object, int inMethodId, int position, int calledMethodId) {

    }

    public void afterMethodCall(int inMethodId, int position, int calledMethodId) {
        BeforeAfterContext beforeAfterContext = new BeforeAfterContext();
        methodRepository.strategyPreAnalyzed(inMethodId).afterMethodCall(beforeAfterContext);
    }

    public void methodEnter(Object object, int methodId) {
        EnterExitContext enterExitContext = new EnterExitContext(object,
                methodId,
                threadLocalWhenInTestAdapter,
                readWriteLockMap,
                parallelizeFacade);
        methodRepository.strategyPreAnalyzed(methodId).methodEnter(enterExitContext);
    }

    public void methodEnterWithIntParam(Object object, int intParam, int methodId) {
        EnterExitContext enterExitContext = new EnterExitContext(object,
                methodId,
                threadLocalWhenInTestAdapter,
                readWriteLockMap,
                parallelizeFacade);
        enterExitContext.setIntParam(intParam);
        methodRepository.strategyPreAnalyzed(methodId).methodEnter(enterExitContext);
    }

    public void methodExit(Object object, int methodId) {
        EnterExitContext enterExitContext = new EnterExitContext(object,
                methodId,
                threadLocalWhenInTestAdapter,
                readWriteLockMap,
                parallelizeFacade);
        methodRepository.strategyPreAnalyzed(methodId).methodExit(enterExitContext);
    }

    public void methodExitObjectReturn(Object returnValue,Object object, int methodId) {
        EnterExitContext enterExitContext = new EnterExitContext(object,
                methodId,
                threadLocalWhenInTestAdapter,
                readWriteLockMap,
                parallelizeFacade);
        enterExitContext.setReturnValue(returnValue);
        methodRepository.strategyPreAnalyzed(methodId).methodExit(enterExitContext);
    }

}
