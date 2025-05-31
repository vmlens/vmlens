package com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class FactoryCollectionAdapterForTransform implements FactoryCollectionAdapter {

    private final FactoryCollection factoryCollection;

    public FactoryCollectionAdapterForTransform(FactoryCollection factoryCollection) {
        this.factoryCollection = factoryCollection;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> get(NameAndDescriptor nameAndDescriptor,
                                                                   int access,
                                                                   int methodId,
                                                                   MethodRepositoryForTransform methodRepositoryForTransform) {
        return factoryCollection.getTransformAndSetStrategy(nameAndDescriptor,
                access, methodId, methodRepositoryForTransform);
    }
}