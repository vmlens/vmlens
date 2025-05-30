package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection;

import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MethodCallbackFactoryFactoryPreAnalyzed;
import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MethodEnterStrategyWithoutParam;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.NotYetImplementedStrategy;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class FactoryCollectionNotYetImplemented implements FactoryCollection {

    private final FactoryTraceMethodEnterExit factoryForBoth;

    public FactoryCollectionNotYetImplemented( MethodRepositoryForTransform methodCallIdMap) {
        this.factoryForBoth = new FactoryTraceMethodEnterExit(
                new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithoutParam()), methodCallIdMap);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor, int access) {
        if ((access & ACC_PUBLIC) != ACC_PUBLIC) {
            return TLinkableWrapper.emptyList();
        }
        return factoryForBoth.getAnalyze(nameAndDescriptor);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(NameAndDescriptor nameAndDescriptor,
                                                                                          int access,
                                                                                          int methodId,
                                                                                          MethodRepositoryForTransform methodRepositoryForTransform) {
        if ((access & ACC_PUBLIC) != ACC_PUBLIC) {
            return TLinkableWrapper.emptyList();
        }
        methodRepositoryForTransform.setStrategyPreAnalyzed(methodId, new NotYetImplementedStrategy(nameAndDescriptor.name()));
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
        factoryForBoth.addToTransform(nameAndDescriptor, result);
        return result;
    }



}
