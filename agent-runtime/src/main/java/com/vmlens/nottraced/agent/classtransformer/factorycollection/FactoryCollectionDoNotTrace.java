package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryDoNotTrace;
import com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter.FactoryCollectionAdapterContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;

public class FactoryCollectionDoNotTrace implements FactoryCollection {

    private final FactoryTraceMethodEnterExit factoryForBoth;

    public FactoryCollectionDoNotTrace(MethodRepositoryForTransform methodCallIdMap) {
        this.factoryForBoth = new FactoryTraceMethodEnterExit(new MethodCallbackFactoryFactoryDoNotTrace(),
                methodCallIdMap);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor, int access) {
        if(doNotTraceInMethod(nameAndDescriptor,access)) {
            return factoryForBoth.addCountTryCatchBlocks(nameAndDescriptor);
        }
        return TLinkableWrapper.emptyList();
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(FactoryCollectionAdapterContext context) {
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
        if(doNotTraceInMethod(context.nameAndDescriptor(),context.access())) {
            factoryForBoth.addTraceMethodEnterExit(context.nameAndDescriptor(), result);
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
