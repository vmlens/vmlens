package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection;

import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public class FactoryCollectionPreAnalyzedFactory implements FactoryCollectionFactory {

    private final THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy;
    private final MethodNotFoundAction methodNotFoundAction;
    private final MethodRepositoryForTransform methodCallIdMap;

    public FactoryCollectionPreAnalyzedFactory(THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy,
                                               MethodNotFoundAction methodNotFoundAction,
                                               MethodRepositoryForTransform methodCallIdMap) {
        this.methodToStrategy = methodToStrategy;
        this.methodNotFoundAction = methodNotFoundAction;
        this.methodCallIdMap = methodCallIdMap;
    }

    @Override
    public FactoryCollection create() {
        return new FactoryCollectionPreAnalyzed(methodToStrategy, methodNotFoundAction, methodCallIdMap);
    }
}
