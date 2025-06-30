package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.AWaitStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public class MethodToStrategy extends AbstractMethodType  {

    public static final AbstractMethodType CONDITION_AWAIT = new MethodToStrategy(new AWaitStrategy());
    
    
    private final StrategyPreAnalyzed strategyPreAnalyzed;

    private MethodToStrategy(StrategyPreAnalyzed strategyPreAnalyzed) {
        this.strategyPreAnalyzed = strategyPreAnalyzed;
    }

    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addPreAnalyzedMethod(name,desc,strategyPreAnalyzed);
    }
}
