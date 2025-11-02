package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.preanalyzed.methodtransformerbuilder.MethodTransformerBuilder;

public abstract class StrategyWithoutParamAndObjectReturn implements StrategyPreAnalyzed  {

    @Override
    public void addToBuilder(MethodTransformerBuilder methodTransformerBuilder) {
        methodTransformerBuilder.withoutParamAndObjectReturn();
    }

}
