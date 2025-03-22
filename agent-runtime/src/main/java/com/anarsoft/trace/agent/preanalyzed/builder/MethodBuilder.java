package com.anarsoft.trace.agent.preanalyzed.builder;

import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionPreAnalyzedFactory;

public interface MethodBuilder {

    void addThreadStart(String name, String desc);

    void addThreadJoin(String name, String desc);

    void noOpWhenMethodNotFound();

    FactoryCollectionPreAnalyzedFactory build();

}
