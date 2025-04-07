package com.anarsoft.trace.agent.preanalyzed.builder;

import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.factory.FactoryCollectionPreAnalyzedFactory;

public interface ClassTransformerListBuilder {

    FactoryCollectionPreAnalyzedFactoryBuilder createTraceNoMethodCall();

    FactoryCollectionPreAnalyzedFactoryBuilder createTraceMethodCallWithObject();



    void addPreAnalyzedEquals(String name, FactoryCollectionPreAnalyzedFactory factoryCollectionPreAnalyzedBuilder);

    void addTraceStartsWith(String name);

    void addFilterStartsWith(String name);

    void addVmlensApi(String name);

}
