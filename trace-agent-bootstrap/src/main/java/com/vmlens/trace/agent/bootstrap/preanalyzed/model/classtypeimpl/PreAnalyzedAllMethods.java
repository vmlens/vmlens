package com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.ClassType;

public class PreAnalyzedAllMethods extends AbstractPreAnalyzed {

    public static final ClassType SINGLETON = new PreAnalyzedAllMethods();

    private PreAnalyzedAllMethods() {}

    @Override
    protected FactoryCollectionPreAnalyzedFactoryBuilder create(ClassTransformerListBuilder classBuilder) {
        return classBuilder.createTraceNoMethodCall();
    }
}