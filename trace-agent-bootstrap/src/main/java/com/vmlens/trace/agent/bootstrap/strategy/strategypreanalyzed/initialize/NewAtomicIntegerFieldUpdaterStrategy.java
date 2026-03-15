package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.initialize;

import com.vmlens.trace.agent.bootstrap.preanalyzed.methodtransformerbuilder.MethodTransformerBuilder;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

/*
 * for the static method call newUpdater(Class<U> tclass, String fieldName)
 */
public class NewAtomicIntegerFieldUpdaterStrategy extends AbstractNewFieldUpdaterStrategy {

    public static final StrategyPreAnalyzed SINGLETON = new NewAtomicIntegerFieldUpdaterStrategy();

    @Override
    public void addToBuilder(MethodTransformerBuilder methodTransformerBuilder) {
        methodTransformerBuilder.setWithObjectStringParamAtReturn();
    }
}
