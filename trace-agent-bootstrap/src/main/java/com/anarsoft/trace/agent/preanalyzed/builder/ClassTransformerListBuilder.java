package com.anarsoft.trace.agent.preanalyzed.builder;


public interface ClassTransformerListBuilder {

    FactoryCollectionPreAnalyzedFactoryBuilder createTraceNoMethodCall();
    
    void addPreAnalyzedEquals(String name, FactoryCollectionPreAnalyzedFactoryBuilder
            factoryCollectionPreAnalyzedFactoryBuilder);

    void addTraceStartsWith(String name);

    void addFilterStartsWith(String name);

    void addVmlensApi(String name);

    void addDoNotTraceIn();

    void addClassNotYetImplemented(String name);

}
