package com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;

public class NotYetImplementedMethod  extends AbstractMethodType {

    public static final NotYetImplementedMethod SINGLETON = new NotYetImplementedMethod();

    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.notYetImplemented(name,desc);
    }
}
