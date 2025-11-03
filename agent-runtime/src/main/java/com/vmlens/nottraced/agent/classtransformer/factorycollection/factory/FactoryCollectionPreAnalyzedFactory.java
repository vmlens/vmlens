package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollectionPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.MethodNotFoundAction;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

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
