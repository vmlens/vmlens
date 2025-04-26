package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.factory;

import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollection;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionDoNotTrace;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;
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
