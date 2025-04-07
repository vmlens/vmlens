package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection;

import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.FactoryFactoryPreAnalyzed;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.preanalyzedstrategy.PreAnalyzedStrategy;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class FactoryCollectionPreAnalyzed implements FactoryCollection {

    private final FactoryForPreAnalyzedAndAll factoryForBoth;
    private final THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy;
    private final MethodNotFoundAction methodNotFoundAction;
    private final PreAnalyzedStrategy preAnalyzedStrategy;

    public FactoryCollectionPreAnalyzed(THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy,
                                        MethodNotFoundAction methodNotFoundAction,
                                        MethodRepositoryForTransform methodCallIdMap,
                                        PreAnalyzedStrategy preAnalyzedStrategy) {
        this.methodToStrategy = methodToStrategy;
        this.methodNotFoundAction = methodNotFoundAction;
        this.factoryForBoth = new FactoryForPreAnalyzedAndAll(
                new FactoryFactoryPreAnalyzed(), methodCallIdMap);
        this.preAnalyzedStrategy = preAnalyzedStrategy;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor) {
        StrategyPreAnalyzed strategy = methodToStrategy.get(nameAndDescriptor);
        if (strategy != null) {
            return factoryForBoth.getAnalyze(nameAndDescriptor);
        }
        switch (methodNotFoundAction) {
            case NO_OP: {
                return TLinkableWrapper.emptyList();
            }
            case WARNING_AND_NOT_TRANSFORM: {
                return factoryForBoth.getAnalyze(nameAndDescriptor);
            }
        }
        return TLinkableWrapper.emptyList();
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(NameAndDescriptor nameAndDescriptor,
                                                                                          int access,
                                                                                          int methodId,
                                                                                          MethodRepositoryForTransform methodRepositoryForTransform) {
        StrategyPreAnalyzed strategy = methodToStrategy.get(nameAndDescriptor);
        if (strategy != null) {
            methodRepositoryForTransform.setStrategyPreAnalyzed(methodId, strategy);
            TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
            factoryForBoth.addToTransform(nameAndDescriptor, result);
            preAnalyzedStrategy.addMethodCallToResult(factoryForBoth,methodRepositoryForTransform,nameAndDescriptor,result);
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
