package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class FactoryCollectionAdapterForAnalyze implements FactoryCollectionAdapter {

    private final FactoryCollection factoryCollection;

    public FactoryCollectionAdapterForAnalyze(FactoryCollection factoryCollection) {
        this.factoryCollection = factoryCollection;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> get(NameAndDescriptor nameAndDescriptor,
                                                                   boolean isSynchronized, int methodId) {
        return factoryCollection.getAnalyze(nameAndDescriptor);
    }
}
