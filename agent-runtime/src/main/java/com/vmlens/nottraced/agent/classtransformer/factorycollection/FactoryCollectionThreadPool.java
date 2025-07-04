package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryDoNotTrace;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit.TryCatchBlockCounter;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorthreadpool.ThreadPoolJoin;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorthreadpool.ThreadPoolThreadStart;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;


public class FactoryCollectionThreadPool implements FactoryCollection {



    private final FactoryTraceMethodEnterExit factoryForBoth;
    private final NameAndDescriptor startThreadMethod;
    private final THashSet<NameAndDescriptor> shutdownMethod;

    public FactoryCollectionThreadPool(NameAndDescriptor startThreadMethod,
                                       THashSet<NameAndDescriptor> shutdownMethod,
                                       MethodRepositoryForTransform methodCallIdMap) {
        this.startThreadMethod = startThreadMethod;
        this.shutdownMethod = shutdownMethod;
        this.factoryForBoth = new FactoryTraceMethodEnterExit(new MethodCallbackFactoryFactoryDoNotTrace(),
                methodCallIdMap);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor,
                                                                          int access) {
        return  factoryForBoth.getAnalyze(nameAndDescriptor);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(NameAndDescriptor nameAndDescriptor,
                                                                                          int access,
                                                                                          int methodId,
                                                                                          MethodRepositoryForTransform methodRepositoryForTransform) {
       if(nameAndDescriptor.equals(startThreadMethod)) {
           return TLinkableWrapper.singleton(ThreadPoolThreadStart.factory());
       }

       if(shutdownMethod.contains(nameAndDescriptor)) {
           return TLinkableWrapper.singleton(ThreadPoolJoin.factory());
       }
       TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
       factoryForBoth.addToTransform(nameAndDescriptor, result);
       return result;
    }

    @Override
    public boolean computeFrames() {
        return true;
    }
}
