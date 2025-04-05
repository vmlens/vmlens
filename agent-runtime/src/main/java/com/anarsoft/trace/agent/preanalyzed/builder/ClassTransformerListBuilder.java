package com.anarsoft.trace.agent.preanalyzed.builder;

import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionPreAnalyzedFactory;

public interface ClassTransformerListBuilder {

    FactoryCollectionPreAnalyzedFactoryBuilder createPreAnalyzedSpecial();

    FactoryCollectionPreAnalyzedFactoryBuilder createPreAnalyzedAtomicNonBlocking();

    FactoryCollectionPreAnalyzedFactoryBuilder createPreAnalyzedAtomicReadWriteLock();


    void addPreAnalyzedEquals(String name, FactoryCollectionPreAnalyzedFactory factoryCollectionPreAnalyzedBuilder);

    void addTraceStartsWith(String name);

    void addFilterStartsWith(String name);

    void addVmlensApi(String name);

}
