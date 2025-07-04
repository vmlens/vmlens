package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.barrierkeytype.BarrierKeyTypeFuture;
import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.*;

public class MethodToStrategy extends AbstractMethodType  {

    public static final AbstractMethodType CONDITION_AWAIT = new MethodToStrategy(new ConditionAWaitStrategy());


    public static final AbstractMethodType FUTURE_GET = new MethodToStrategy(new BarrierWaitStrategy(BarrierKeyTypeFuture.SINGLETON));
    public static final AbstractMethodType FUTURE_SET = new MethodToStrategy(new BarrierNotifyStrategy(
            BarrierKeyTypeFuture.SINGLETON));
    public static final AbstractMethodType FUTURE_RUN = new MethodToStrategy(new FutureRunStrategy());
    
    
    private final StrategyPreAnalyzed strategyPreAnalyzed;

    private MethodToStrategy(StrategyPreAnalyzed strategyPreAnalyzed) {
        this.strategyPreAnalyzed = strategyPreAnalyzed;
    }

    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addPreAnalyzedMethod(name,desc,strategyPreAnalyzed);
    }
}
