package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForCallback;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.BeforeAfterContext;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.EnterExitContext;

public class PreAnalyzedCallbackImpl {

    private final MethodRepositoryForCallback methodRepository;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public PreAnalyzedCallbackImpl(MethodRepositoryForCallback methodRepository,
                                   ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.methodRepository = methodRepository;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public void beforeMethodCall(int inMethodId, int position, int calledMethodId) {
        BeforeAfterContext beforeAfterContext = new BeforeAfterContext();
        methodRepository.strategyPreAnalyzed(inMethodId).beforeMethodCall(beforeAfterContext);
    }

    public void afterMethodCall(int inMethodId, int position, int calledMethodId) {
        BeforeAfterContext beforeAfterContext = new BeforeAfterContext();
        methodRepository.strategyPreAnalyzed(inMethodId).afterMethodCall(beforeAfterContext);
    }

    public void methodEnter(Object object, int methodId) {
        EnterExitContext enterExitContext = new EnterExitContext(object, methodId, threadLocalWhenInTestAdapter);
        methodRepository.strategyPreAnalyzed(methodId).methodEnter(enterExitContext);
    }

    public void methodExit(Object object, int methodId) {
        EnterExitContext enterExitContext = new EnterExitContext(object, methodId, threadLocalWhenInTestAdapter);
        methodRepository.strategyPreAnalyzed(methodId).methodExit(enterExitContext);
    }

}
