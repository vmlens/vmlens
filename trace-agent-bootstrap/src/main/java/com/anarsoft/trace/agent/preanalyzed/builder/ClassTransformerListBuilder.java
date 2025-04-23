package com.anarsoft.trace.agent.preanalyzed.builder;


public interface ClassTransformerListBuilder {

    FactoryCollectionPreAnalyzedFactoryBuilder createTraceNoMethodCall();

    FactoryCollectionPreAnalyzedFactoryBuilder createTraceMethodCallWithObject();

    void addPreAnalyzedEquals(String name, FactoryCollectionPreAnalyzedFactoryBuilder
            factoryCollectionPreAnalyzedFactoryBuilder);

    void addTraceStartsWith(String name);

    void addFilterStartsWith(String name);

    void addVmlensApi(String name);

}
