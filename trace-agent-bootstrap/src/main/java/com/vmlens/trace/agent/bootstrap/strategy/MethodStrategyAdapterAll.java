package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForCallback;

public class MethodStrategyAdapterAll implements MethodStrategyAdapter {

    private final MethodRepositoryForCallback methodIdToStrategy;

    public MethodStrategyAdapterAll(MethodRepositoryForCallback methodIdToStrategy) {
        this.methodIdToStrategy = methodIdToStrategy;
    }

    @Override
    public void methodEnter(MethodContext enterContext) {
        methodIdToStrategy.strategyAll(enterContext.methodId()).methodEnter(enterContext);
    }

    @Override
    public void methodExit(MethodContext exitContext) {
        methodIdToStrategy.strategyAll(exitContext.methodId()).methodExit(exitContext);
    }
}
