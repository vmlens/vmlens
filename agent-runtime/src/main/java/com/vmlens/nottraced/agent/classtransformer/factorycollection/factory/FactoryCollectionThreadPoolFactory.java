package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollectionThreadPool;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.strategy.threadpool.StrategyThreadPool;

public class FactoryCollectionThreadPoolFactory implements FactoryCollectionFactory {

    private final NameAndDescriptor startThreadMethod;
    private final THashMap<NameAndDescriptor, StrategyThreadPool> shutdownMethodToStrategy;

    public FactoryCollectionThreadPoolFactory(NameAndDescriptor startThreadMethod,
                                              THashMap<NameAndDescriptor, StrategyThreadPool> shutdownMethodToStrategy) {
        this.startThreadMethod = startThreadMethod;
        this.shutdownMethodToStrategy = shutdownMethodToStrategy;
    }

    @Override
    public FactoryCollection create() {
        return new FactoryCollectionThreadPool(startThreadMethod,shutdownMethodToStrategy);
    }
}
