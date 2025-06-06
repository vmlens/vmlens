package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.NormalMethodStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.RunMethodStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.SynchronizedMethodStrategy;

public class OnMethodAccess {

    private final MethodRepositoryForTransform methodRepositoryForAnalyze;
    private final int id;


    public OnMethodAccess(MethodRepositoryForTransform methodRepositoryForAnalyze, int id) {
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
        this.id = id;
    }

    public void onSynchronized() {
        methodRepositoryForAnalyze.setStrategyAll(id, SynchronizedMethodStrategy.SINGLETON);
    }

    public void onSynchronizedAndThreadRun() {
        methodRepositoryForAnalyze.setStrategyAll(id, new RunMethodStrategy(SynchronizedMethodStrategy.SINGLETON));
    }

    public void onNotSynchronized() {
        methodRepositoryForAnalyze.setStrategyAll(id, NormalMethodStrategy.SINGLETON);
    }

    public void onThreadRun() {
        methodRepositoryForAnalyze.setStrategyAll(id, new RunMethodStrategy(NormalMethodStrategy.SINGLETON));
    }

}
