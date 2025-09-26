package com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;

public class FactoryCollectionAdapterContext {

    private final String className;
    private final NameAndDescriptor nameAndDescriptor;
    private final int access;
    private final int methodId;
    private final MethodRepositoryForTransform methodRepositoryForTransform;

    public FactoryCollectionAdapterContext(String className,
                                           NameAndDescriptor nameAndDescriptor,
                                           int access,
                                           int methodId,
                                           MethodRepositoryForTransform methodRepositoryForTransform) {
        this.className = className;
        this.nameAndDescriptor = nameAndDescriptor;
        this.access = access;
        this.methodId = methodId;
        this.methodRepositoryForTransform = methodRepositoryForTransform;
    }

    public String className() {
        return className;
    }

    public NameAndDescriptor nameAndDescriptor() {
        return nameAndDescriptor;
    }

    public int access() {
        return access;
    }

    public int methodId() {
        return methodId;
    }

    public MethodRepositoryForTransform methodRepositoryForTransform() {
        return methodRepositoryForTransform;
    }
}
