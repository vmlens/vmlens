package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollectionDoNotTrace;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;

public class FactoryCollectionDoNotTraceFactory  implements FactoryCollectionFactory  {

    private final MethodRepositoryForTransform methodCallIdMap;

    public FactoryCollectionDoNotTraceFactory(MethodRepositoryForTransform methodCallIdMap) {
        this.methodCallIdMap = methodCallIdMap;
    }

    @Override
    public FactoryCollection create() {
        return new FactoryCollectionDoNotTrace(methodCallIdMap);
    }
}
