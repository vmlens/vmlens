package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollectionThreadPool;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;


public class FactoryCollectionThreadPoolFactory implements FactoryCollectionFactory {

    private final NameAndDescriptor startThreadMethod;
    private final THashSet<NameAndDescriptor> shutdownMethodToStrategy;
    private final MethodRepositoryForTransform methodCallIdMap;

    public FactoryCollectionThreadPoolFactory(NameAndDescriptor startThreadMethod,
                                              THashSet<NameAndDescriptor> shutdownMethodToStrategy, MethodRepositoryForTransform methodCallIdMap) {
        this.startThreadMethod = startThreadMethod;
        this.shutdownMethodToStrategy = shutdownMethodToStrategy;
        this.methodCallIdMap = methodCallIdMap;
    }

    @Override
    public FactoryCollection create() {
        return new FactoryCollectionThreadPool(startThreadMethod,shutdownMethodToStrategy,methodCallIdMap);
    }
}
