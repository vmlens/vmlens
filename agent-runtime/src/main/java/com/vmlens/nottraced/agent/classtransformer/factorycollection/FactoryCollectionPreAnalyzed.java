package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodEnterStrategyWithIntParam;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodEnterStrategyWithoutParam;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.preanalyzedstrategy.SelectMethodEnterStrategy;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class FactoryCollectionPreAnalyzed extends FactoryCollectionPreAnalyzedOrAll {

    private final FactoryTraceMethodEnterExit factoryTraceMethodEnterExit;
    private final FactoryTraceMethodEnterExit factoryTraceMethodEnterWithIntParamExit;
    private final THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy;
    private final MethodNotFoundAction methodNotFoundAction;
    private final SelectMethodEnterStrategy preAnalyzedStrategy;

    public FactoryCollectionPreAnalyzed(THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy,
                                        MethodNotFoundAction methodNotFoundAction,
                                        MethodRepositoryForTransform methodCallIdMap,
                                        SelectMethodEnterStrategy preAnalyzedStrategy) {
        super(methodCallIdMap);
        this.methodToStrategy = methodToStrategy;
        this.methodNotFoundAction = methodNotFoundAction;
        this.factoryTraceMethodEnterExit = new FactoryTraceMethodEnterExit(
                new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithoutParam()), methodCallIdMap);
        this.factoryTraceMethodEnterWithIntParamExit = new FactoryTraceMethodEnterExit(
                new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithIntParam()), methodCallIdMap);
        this.preAnalyzedStrategy = preAnalyzedStrategy;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyzeAfterFilter(NameAndDescriptor nameAndDescriptor, int access) {
        StrategyPreAnalyzed strategy = methodToStrategy.get(nameAndDescriptor);
        if (strategy != null) {
            if(preAnalyzedStrategy.useWithInParam(nameAndDescriptor)) {
                return factoryTraceMethodEnterWithIntParamExit.addCountTryCatchBlocks(nameAndDescriptor);
            }
            return factoryTraceMethodEnterExit.addCountTryCatchBlocks(nameAndDescriptor);
        }
        switch (methodNotFoundAction) {
            case NO_OP: {
                return TLinkableWrapper.emptyList();
            }
            case WARNING_AND_NOT_TRANSFORM: {
                return factoryTraceMethodEnterExit.addCountTryCatchBlocks(nameAndDescriptor);
            }
        }
        return TLinkableWrapper.emptyList();
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategyAfterFilter(NameAndDescriptor nameAndDescriptor,
                                                                                          int access,
                                                                                          int methodId,
                                                                                          MethodRepositoryForTransform methodRepositoryForTransform) {
        StrategyPreAnalyzed strategy = methodToStrategy.get(nameAndDescriptor);
        if (strategy != null) {
            methodRepositoryForTransform.setStrategyPreAnalyzed(methodId, strategy);
            TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
            if(preAnalyzedStrategy.useWithInParam(nameAndDescriptor)) {
                factoryTraceMethodEnterWithIntParamExit.addTraceMethodEnterExit(nameAndDescriptor, result);
            } else {
                factoryTraceMethodEnterExit.addTraceMethodEnterExit(nameAndDescriptor, result);
            }
            return result;
        }
        switch (methodNotFoundAction) {
            case NO_OP: {
                return TLinkableWrapper.emptyList();
            }
            case WARNING_AND_NOT_TRANSFORM: {
                // Fixme create warning when public method
                return TLinkableWrapper.emptyList();
            }
        }
        return TLinkableWrapper.emptyList();
    }
}
