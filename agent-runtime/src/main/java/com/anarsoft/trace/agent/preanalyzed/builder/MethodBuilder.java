package com.anarsoft.trace.agent.preanalyzed.builder;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.FactoryCollectionPreAnalyzedFactory;

public interface MethodBuilder {

    void addThreadStart(String name, String desc);

    void noOpWhenMethodNotFound();

    FactoryCollectionPreAnalyzedFactory build();

}
