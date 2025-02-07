package com.anarsoft.trace.agent.preanalyzed.builder;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.FactoryCollectionPreAnalyzedFactory;

public interface ClassBuilder {

    MethodBuilder createMethodBuilder();

    void addPreAnalyzedEquals(String name, FactoryCollectionPreAnalyzedFactory factoryCollectionPreAnalyzedBuilder);

    void addTraceStartsWith(String name);

    void addFilterStartsWith(String name);

    void addVmlensApi(String name);

}
