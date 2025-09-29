package com.vmlens.trace.agent.bootstrap.preanalyzed.builder;


public interface ClassTransformerListBuilder {

    FactoryCollectionPreAnalyzedFactoryBuilder createTraceNoMethodCall();
    
    void addPreAnalyzedEquals(String name,
                              FactoryCollectionPreAnalyzedFactoryBuilder factoryCollectionPreAnalyzedFactoryBuilder);

    void addThreadPool(String name,
                       FactoryCollectionPreAnalyzedFactoryBuilder factoryCollectionPreAnalyzedFactoryBuilder);

    void addTraceStartsWith(String name);

    void addFilterStartsWith(String name);

    void addVMLensApi(String name);

    void addDoNotTraceIn(String name);

    void addDoNotTraceInTestContainsClassName(String name);

    void addDoNotTraceInTestStartWithClassName(String name);

    void addClassNotYetImplemented(String name);

    void addFilterInnerIncludeAnonymous(String name);

}
