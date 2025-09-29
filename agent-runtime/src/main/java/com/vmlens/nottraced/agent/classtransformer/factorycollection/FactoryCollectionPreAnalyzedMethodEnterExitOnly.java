package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodEnterStrategyWithoutParam;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.factory.PreAnalyzedStrategyFactory;
import com.vmlens.nottraced.agent.classtransformer.factorycollectionadapter.FactoryCollectionAdapterContext;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class FactoryCollectionPreAnalyzedMethodEnterExitOnly implements FactoryCollection {

    private final FactoryTraceMethodEnterExit factoryForBoth;
    private final PreAnalyzedStrategyFactory preAnalyzedStrategyFactory;

    public FactoryCollectionPreAnalyzedMethodEnterExitOnly(MethodRepositoryForTransform methodCallIdMap,
                                                           PreAnalyzedStrategyFactory preAnalyzedStrategyFactory) {
        this.factoryForBoth = new FactoryTraceMethodEnterExit(
                new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithoutParam()), methodCallIdMap);
        this.preAnalyzedStrategyFactory = preAnalyzedStrategyFactory;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor, int access) {
        if ((access & ACC_PUBLIC) != ACC_PUBLIC) {
            return TLinkableWrapper.emptyList();
        }
        return factoryForBoth.addCountTryCatchBlocks(nameAndDescriptor);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(FactoryCollectionAdapterContext context) {
        if ((context.access() & ACC_PUBLIC) != ACC_PUBLIC) {
            return TLinkableWrapper.emptyList();
        }
        context.methodRepositoryForTransform().setStrategyPreAnalyzed(context.methodId(),
                preAnalyzedStrategyFactory.create(context.className(),context.nameAndDescriptor()));
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
        factoryForBoth.addTraceMethodEnterExit(context.nameAndDescriptor(), result);
        return result;
    }

    @Override
    public boolean computeFrames() {
        return false;
    }

}
