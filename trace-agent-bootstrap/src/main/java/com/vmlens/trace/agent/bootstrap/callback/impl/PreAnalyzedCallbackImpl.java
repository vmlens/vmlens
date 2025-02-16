package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForCallback;

public class PreAnalyzedCallbackImpl {

    private final MethodRepositoryForCallback methodRepository;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public PreAnalyzedCallbackImpl(MethodRepositoryForCallback methodRepository,
                                   ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.methodRepository = methodRepository;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public void beforeMethodCall(int calledMethodId) {
    }

    public void afterMethodCall(int inMethodId, int position, int calledMethodId) {

    }

    public void methodEnter(Object object, int methodId) {

    }

    public void methodExit(Object object, int methodId) {

    }

}
