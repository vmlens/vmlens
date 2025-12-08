package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.preanalyzed.methodtransformerbuilder.MethodTransformerBuilder;

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
