package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.factory;

import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollection;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionPreAnalyzed;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.MethodNotFoundAction;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.preanalyzedstrategy.PreAnalyzedStrategy;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public class FactoryCollectionPreAnalyzedFactory implements FactoryCollectionFactory {

    private final THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy;
    private final MethodNotFoundAction methodNotFoundAction;
    private final MethodRepositoryForTransform methodCallIdMap;
    private final PreAnalyzedStrategy preAnalyzedStrategy;

    public FactoryCollectionPreAnalyzedFactory(THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy,
                                               MethodNotFoundAction methodNotFoundAction,
                                               MethodRepositoryForTransform methodCallIdMap,
                                               PreAnalyzedStrategy preAnalyzedStrategy) {
        this.methodToStrategy = methodToStrategy;
        this.methodNotFoundAction = methodNotFoundAction;
        this.methodCallIdMap = methodCallIdMap;
        this.preAnalyzedStrategy = preAnalyzedStrategy;
    }

    @Override
    public FactoryCollection create() {
        return new FactoryCollectionPreAnalyzed(methodToStrategy, methodNotFoundAction, methodCallIdMap,preAnalyzedStrategy);
    }
}
