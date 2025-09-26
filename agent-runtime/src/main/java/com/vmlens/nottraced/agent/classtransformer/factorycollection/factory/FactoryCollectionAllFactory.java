package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;


import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollectionAll;
import com.vmlens.transformed.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;

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
