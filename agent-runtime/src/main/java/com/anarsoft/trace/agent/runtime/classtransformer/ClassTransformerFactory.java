package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionFactory;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import org.objectweb.asm.ClassVisitor;

public class ClassTransformerFactory {

    private final FactoryCollectionFactory factoryCollectionFactory;
    private final MethodRepositoryForTransform methodCallIdMap;

    public ClassTransformerFactory(FactoryCollectionFactory factoryCollectionFactory,
                                   MethodRepositoryForTransform methodCallIdMap) {
        this.factoryCollectionFactory = factoryCollectionFactory;
        this.methodCallIdMap = methodCallIdMap;
    }

    public ClassTransformer create(ClassVisitor previousClassVisitor) {
        return new ClassTransformer(factoryCollectionFactory.create(),
                methodCallIdMap,
                previousClassVisitor);
    }

}
