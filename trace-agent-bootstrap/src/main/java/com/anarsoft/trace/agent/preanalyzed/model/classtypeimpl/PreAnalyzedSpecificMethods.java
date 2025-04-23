package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.ClassType;

/**
 *
 * Used for Thread and ReentrantReadWriteLock and ReentrantLock
 * e.g. classes where we are only interested in the method calls not calls
 * inside the method
 * examples:
 *    Thread.join
 *    lock.enter
 *
 */


public class PreAnalyzedSpecificMethods extends AbstractPreAnalyzed {

    public static final ClassType SINGLETON = new PreAnalyzedSpecificMethods();

    private PreAnalyzedSpecificMethods() {}

    @Override
    protected FactoryCollectionPreAnalyzedFactoryBuilder create(ClassTransformerListBuilder classBuilder) {
        FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder = classBuilder.createTraceNoMethodCall();
        methodBuilder.noOpWhenMethodNotFound();
        return methodBuilder;
    }
}
