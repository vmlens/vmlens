package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.ClassType;

public class PreAnalyzedAtomicReadWriteLock extends AbstractPreAnalyzed {

    public static final ClassType SINGLETON = new PreAnalyzedAtomicReadWriteLock();

    private PreAnalyzedAtomicReadWriteLock() {}

    @Override
    protected FactoryCollectionPreAnalyzedFactoryBuilder create(ClassTransformerListBuilder classBuilder) {
        return classBuilder.createTraceNoMethodCall();
    }
}