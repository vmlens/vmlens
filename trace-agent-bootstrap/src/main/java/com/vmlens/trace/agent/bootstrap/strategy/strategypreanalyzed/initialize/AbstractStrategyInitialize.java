package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.initialize;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.initializationaction.StrategyInitialize;
import com.vmlens.trace.agent.bootstrap.preanalyzed.methodtransformerbuilder.MethodTransformerBuilder;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public abstract class AbstractStrategyInitialize implements StrategyPreAnalyzed, StrategyInitialize {

    @Override
    public void addToBuilder(MethodTransformerBuilder methodTransformerBuilder) {
        methodTransformerBuilder.setWithoutParamForInitialize();
    }

}
