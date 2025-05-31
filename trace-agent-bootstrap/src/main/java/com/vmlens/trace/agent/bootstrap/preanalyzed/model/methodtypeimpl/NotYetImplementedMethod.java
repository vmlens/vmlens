package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;

public class NotYetImplementedMethod  extends AbstractMethodType {

    public static final NotYetImplementedMethod SINGLETON = new NotYetImplementedMethod();

    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.notYetImplemented(name,desc);
    }
}
