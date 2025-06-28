package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.NotYetImplementedStrategy;

public class NotYetImplementedMethod  extends AbstractMethodType {

    public static final NotYetImplementedMethod SINGLETON = new NotYetImplementedMethod();

    private NotYetImplementedMethod() {
    }

    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addPreAnalyzedMethod(name,desc,new NotYetImplementedStrategy(name));
    }
}
