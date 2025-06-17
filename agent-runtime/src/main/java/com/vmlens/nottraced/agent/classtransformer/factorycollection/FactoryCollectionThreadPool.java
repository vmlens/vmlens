package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorthreadpool.ThreadPoolJoin;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorthreadpool.ThreadPoolThreadStart;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.strategy.threadpool.StrategyThreadPool;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class FactoryCollectionThreadPool implements FactoryCollection {

    private final NameAndDescriptor startThreadMethod;
    private final THashMap<NameAndDescriptor, StrategyThreadPool> shutdownMethodToStrategy;

    public FactoryCollectionThreadPool(NameAndDescriptor startThreadMethod,
                                       THashMap<NameAndDescriptor, StrategyThreadPool> shutdownMethodToStrategy) {
        this.startThreadMethod = startThreadMethod;
        this.shutdownMethodToStrategy = shutdownMethodToStrategy;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor,
                                                                          int access) {
        return TLinkableWrapper.emptyList();
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(NameAndDescriptor nameAndDescriptor,
                                                                                          int access,
                                                                                          int methodId,
                                                                                          MethodRepositoryForTransform methodRepositoryForTransform) {
       if(nameAndDescriptor.equals(startThreadMethod)) {
           return TLinkableWrapper.singleton(ThreadPoolThreadStart.factory());
       }

       StrategyThreadPool strategy = shutdownMethodToStrategy.get(nameAndDescriptor);
       if(strategy != null) {
           methodRepositoryForTransform.setStrategyThreadPool(methodId,strategy);
           return TLinkableWrapper.singleton(ThreadPoolJoin.factory());
       }
        return TLinkableWrapper.emptyList();
    }

    @Override
    public boolean computeFrames() {
        return true;
    }
}
