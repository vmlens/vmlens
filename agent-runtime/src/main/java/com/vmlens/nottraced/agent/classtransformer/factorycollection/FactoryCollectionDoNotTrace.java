package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryDoNotTrace;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class FactoryCollectionDoNotTrace implements FactoryCollection {

    private final FactoryTraceMethodEnterExit factoryForBoth;


    public FactoryCollectionDoNotTrace(MethodRepositoryForTransform methodCallIdMap) {


        this.factoryForBoth = new FactoryTraceMethodEnterExit(new MethodCallbackFactoryFactoryDoNotTrace(),
                methodCallIdMap);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor, int access) {
        if(doNotTraceInMethod(nameAndDescriptor,access)) {
            return factoryForBoth.getAnalyze(nameAndDescriptor);
        }
        return TLinkableWrapper.emptyList();
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(NameAndDescriptor nameAndDescriptor,
                                                                                          int access,
                                                                                          int methodId,
                                                                                          MethodRepositoryForTransform methodRepositoryForTransform) {
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
        if(doNotTraceInMethod(nameAndDescriptor,access)) {
            factoryForBoth.addToTransform(nameAndDescriptor, result);
        }
        return result;
    }

    private boolean doNotTraceInMethod(NameAndDescriptor nameAndDescriptor, int access) {
        return true;
    }

    @Override
    public boolean computeFrames() {
        return false;
    }

}
