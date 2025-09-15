package com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter;

import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class FactoryCollectionAdapterForAnalyze implements FactoryCollectionAdapter {

    private final FactoryCollection factoryCollection;

    public FactoryCollectionAdapterForAnalyze(FactoryCollection factoryCollection) {
        this.factoryCollection = factoryCollection;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> get(FactoryCollectionAdapterContext context) {
        return factoryCollection.getAnalyze(context.nameAndDescriptor(),context.access());
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getForConstructor(FactoryCollectionAdapterContext context) {
        return new TLinkedList<TLinkableWrapper<MethodVisitorFactory>>();
    }
}
