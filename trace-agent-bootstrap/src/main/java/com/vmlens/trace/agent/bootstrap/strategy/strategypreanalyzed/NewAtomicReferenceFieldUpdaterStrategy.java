package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.preanalyzed.methodtransformerbuilder.MethodTransformerBuilder;

/*
 * for the static method call newUpdater(Class<U> tclass, Class<W> vclass, String fieldName)
 * where tclass - the class of the objects holding the field
 */
public class NewAtomicReferenceFieldUpdaterStrategy  extends AbstractNewFieldUpdaterStrategy  {

    public static final StrategyPreAnalyzed SINGLETON = new NewAtomicReferenceFieldUpdaterStrategy();
    
    @Override
    public void addToBuilder(MethodTransformerBuilder methodTransformerBuilder) {
        methodTransformerBuilder.setWithObjectPlaceHolderStringParamAtReturn();
    }
}
