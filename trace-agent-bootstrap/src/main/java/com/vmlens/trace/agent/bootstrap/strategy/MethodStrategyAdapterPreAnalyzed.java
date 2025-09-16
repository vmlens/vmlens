package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForCallback;

public class MethodStrategyAdapterPreAnalyzed implements MethodStrategyAdapter {

    private final ReadWriteLockMap readWriteLockMap;
    private final  MethodRepositoryForCallback methodRepositoryForCallback;

    public MethodStrategyAdapterPreAnalyzed(ReadWriteLockMap readWriteLockMap,
                                            MethodRepositoryForCallback methodRepositoryForCallback) {
        this.readWriteLockMap = readWriteLockMap;
        this.methodRepositoryForCallback = methodRepositoryForCallback;
    }

    @Override
    public void methodEnter(MethodContext enterExitContext) {
        methodRepositoryForCallback.
                strategyPreAnalyzed(enterExitContext.methodId()).
                methodEnter(enterExitContext.toEnterExitContext(readWriteLockMap));
    }

    @Override
    public void methodExit(MethodContext enterExitContext) {
        methodRepositoryForCallback.
                strategyPreAnalyzed(enterExitContext.methodId()).
                methodExit(enterExitContext.toEnterExitContext(readWriteLockMap));
    }
}
