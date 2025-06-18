package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryDoNotTrace;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public abstract class FactoryCollectionPreAnalyzedOrAll  implements FactoryCollection {

    private final FactoryTraceMethodEnterExit factoryForDoNotTrace;

    public FactoryCollectionPreAnalyzedOrAll(MethodRepositoryForTransform methodCallIdMap) {
        this.factoryForDoNotTrace = new FactoryTraceMethodEnterExit(new MethodCallbackFactoryFactoryDoNotTrace(),
                methodCallIdMap);
    }

    @Override
    public final TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor,
                                                                          int access) {
        if(doNotTraceIn(nameAndDescriptor)) {
            return factoryForDoNotTrace.getAnalyze(nameAndDescriptor);
        }
        return getAnalyzeAfterFilter(nameAndDescriptor,access);
    }

    @Override
    public final TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(NameAndDescriptor nameAndDescriptor,
                                                                                          int access,
                                                                                          int methodId,
                                                                                          MethodRepositoryForTransform methodRepositoryForTransform) {
        if(doNotTraceIn(nameAndDescriptor)) {
            TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
            factoryForDoNotTrace.addToTransform(nameAndDescriptor, result);
            return result;
        }
        return getTransformAndSetStrategyAfterFilter(nameAndDescriptor,access,methodId,methodRepositoryForTransform);
    }

    protected abstract TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyzeAfterFilter
            (NameAndDescriptor nameAndDescriptor, int access);

    protected abstract  TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategyAfterFilter
            (NameAndDescriptor nameAndDescriptor, int access, int methodId,
             MethodRepositoryForTransform methodRepositoryForTransform);

    private boolean doNotTraceIn(NameAndDescriptor nameAndDescriptor) {
        return nameAndDescriptor.name().equals("<clinit>");
    }

    @Override
    public boolean computeFrames() {
        return false;
    }

}