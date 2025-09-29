package com.vmlens.nottraced.agent.applyclasstransformer.builder;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.MethodNotFoundAction;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.factory.FactoryCollectionPreAnalyzedFactory;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.factory.FactoryCollectionThreadPoolFactory;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.preanalyzedstrategy.SelectMethodEnterStrategy;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.transformed.agent.bootstrap.strategy.strategypreanalyzed.NonBlockingStrategy;
import com.vmlens.transformed.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

import static com.vmlens.nottraced.agent.classtransformer.factorycollection.MethodNotFoundAction.NO_OP;
import static com.vmlens.nottraced.agent.classtransformer.factorycollection.MethodNotFoundAction.WARNING_AND_NOT_TRANSFORM;

public class FactoryCollectionPreAnalyzedFactoryBuilderImpl implements FactoryCollectionPreAnalyzedFactoryBuilder {

    private final THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy = new
            THashMap<>();
    private final THashSet<NameAndDescriptor> methodToStrategyForThreadPool = new
            THashSet<>();
    private final MethodRepositoryForTransform methodCallIdMap;
    private final SelectMethodEnterStrategy preAnalyzedStrategy;
    private MethodNotFoundAction methodNotFoundAction = WARNING_AND_NOT_TRANSFORM;
    private NameAndDescriptor startThreadMethod;

    public FactoryCollectionPreAnalyzedFactoryBuilderImpl(MethodRepositoryForTransform methodCallIdMap,
                                                          SelectMethodEnterStrategy preAnalyzedStrategy) {
        this.methodCallIdMap = methodCallIdMap;
        this.preAnalyzedStrategy = preAnalyzedStrategy;
    }

    @Override
    public void addNonBlockingArrayMethod(String name, String desc, int operation) {
        NameAndDescriptor nameAndDescriptor = new NameAndDescriptor(name, desc);
        preAnalyzedStrategy.addToUseWithInParam(nameAndDescriptor);
        methodToStrategy.put(nameAndDescriptor, new NonBlockingStrategy(operation));
    }

    @Override
    public void addPreAnalyzedMethod(String name, String desc, StrategyPreAnalyzed strategyPreAnalyzed) {
        methodToStrategy.put(new NameAndDescriptor(name, desc), strategyPreAnalyzed);
    }


    @Override
    public void noOpWhenMethodNotFound() {
        methodNotFoundAction = NO_OP;
    }

    @Override
    public void setThreadPoolStart(String name, String desc) {
        this.startThreadMethod = new NameAndDescriptor(name,desc);
    }

    @Override
    public void addThreadPoolJoin(String name, String desc) {
        methodToStrategyForThreadPool.add(new NameAndDescriptor(name, desc));
    }

    public FactoryCollectionPreAnalyzedFactory build() {
        return new FactoryCollectionPreAnalyzedFactory(methodToStrategy, methodNotFoundAction, methodCallIdMap,preAnalyzedStrategy);
    }

    public FactoryCollectionThreadPoolFactory buildForThreadPool() {
        return new FactoryCollectionThreadPoolFactory(startThreadMethod,  methodToStrategyForThreadPool,methodCallIdMap);
    }
}
