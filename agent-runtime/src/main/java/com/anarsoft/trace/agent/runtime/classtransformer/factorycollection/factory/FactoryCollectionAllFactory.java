package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.factory;


import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollection;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionAll;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;

public class FactoryCollectionAllFactory implements FactoryCollectionFactory {

    private final FieldRepositoryForTransform fieldIdMap;
    private final MethodRepositoryForTransform methodCallIdMap;

    public FactoryCollectionAllFactory(FieldRepositoryForTransform fieldIdMap,
                                       MethodRepositoryForTransform methodCallIdMap) {
        this.fieldIdMap = fieldIdMap;
        this.methodCallIdMap = methodCallIdMap;
    }

    @Override
    public FactoryCollection create() {

        return new FactoryCollectionAll(fieldIdMap, methodCallIdMap);
    }
}
