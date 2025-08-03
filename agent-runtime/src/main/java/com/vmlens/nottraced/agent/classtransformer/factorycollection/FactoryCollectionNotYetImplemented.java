package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodEnterStrategyWithoutParam;
import com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter.FactoryCollectionAdapterContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
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
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(FactoryCollectionAdapterContext context) {
        if ((context.access() & ACC_PUBLIC) != ACC_PUBLIC) {
            return TLinkableWrapper.emptyList();
        }
        context.methodRepositoryForTransform().setStrategyPreAnalyzed(context.methodId(),
                new NotYetImplementedStrategy(context.className(), context.nameAndDescriptor().name()));
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
        factoryForBoth.addToTransform(context.nameAndDescriptor(), result);
        return result;
    }

    @Override
    public boolean computeFrames() {
        return false;
    }

}
