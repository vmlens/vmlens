package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollectionNotYetImplemented;
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
