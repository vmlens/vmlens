package com.vmlens.trace.agent.bootstrap.callback.callbackaction.initializationaction;

import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForCallback;

public class InitializeBasedOnStrategy implements InitializationAction{

    private final StrategyInitializeContext strategyInitializeContext;
    private final MethodRepositoryForCallback methodRepositoryForCallback;
    private final int methodId;

    public InitializeBasedOnStrategy(StrategyInitializeContext strategyInitializeContext,
                                     MethodRepositoryForCallback methodRepositoryForCallback,
                                     int methodId) {
        this.strategyInitializeContext = strategyInitializeContext;
        this.methodRepositoryForCallback = methodRepositoryForCallback;
        this.methodId = methodId;
    }

    @Override
    public void initialize() {
        StrategyInitialize strategy = (StrategyInitialize) methodRepositoryForCallback.strategyPreAnalyzed(methodId);
        strategy.intitialize(strategyInitializeContext);
    }
}
