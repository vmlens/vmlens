package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.factory;

import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollection;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionNotYetImplemented;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;

public class FactoryCollectionNotYetImplementedFactory implements FactoryCollectionFactory {

    private final MethodRepositoryForTransform methodRepositoryForAnalyze;

    public FactoryCollectionNotYetImplementedFactory(MethodRepositoryForTransform methodRepositoryForAnalyze) {
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
    }

    @Override
    public FactoryCollection create() {
        return new FactoryCollectionNotYetImplemented(methodRepositoryForAnalyze);
    }
}
