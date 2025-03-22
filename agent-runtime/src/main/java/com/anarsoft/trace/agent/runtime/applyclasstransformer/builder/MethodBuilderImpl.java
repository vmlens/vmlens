package com.anarsoft.trace.agent.runtime.applyclasstransformer.builder;

import com.anarsoft.trace.agent.preanalyzed.builder.MethodBuilder;
import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionPreAnalyzedFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.MethodNotFoundAction;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.ThreadJoinStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.ThreadStartStrategy;

import static com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.MethodNotFoundAction.NO_OP;
import static com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.MethodNotFoundAction.WARNING_AND_TRANSFORM;

public class MethodBuilderImpl implements MethodBuilder {

    private final THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy = new
            THashMap<>();
    private final MethodRepositoryForTransform methodCallIdMap;
    private MethodNotFoundAction methodNotFoundAction = WARNING_AND_TRANSFORM;

    public MethodBuilderImpl(MethodRepositoryForTransform methodCallIdMap) {
        this.methodCallIdMap = methodCallIdMap;
    }


    @Override
    public void addThreadStart(String name, String desc) {
        methodToStrategy.put(new NameAndDescriptor(name, desc), ThreadStartStrategy.SINGLETON);
    }

    @Override
    public void addThreadJoin(String name, String desc) {
        methodToStrategy.put(new NameAndDescriptor(name, desc), ThreadJoinStrategy.SINGLETON);
    }

    @Override
    public void noOpWhenMethodNotFound() {
        methodNotFoundAction = NO_OP;
    }

    @Override
    public FactoryCollectionPreAnalyzedFactory build() {
        return new FactoryCollectionPreAnalyzedFactory(methodToStrategy, methodNotFoundAction, methodCallIdMap);
    }
}
