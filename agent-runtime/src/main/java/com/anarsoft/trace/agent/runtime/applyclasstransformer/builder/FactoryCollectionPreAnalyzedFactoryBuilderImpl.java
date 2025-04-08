package com.anarsoft.trace.agent.runtime.applyclasstransformer.builder;

import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.preanalyzedstrategy.PreAnalyzedStrategy;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.CallbackInNonBlockingMethod;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.factory.FactoryCollectionPreAnalyzedFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.MethodNotFoundAction;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.lock.LockType;
import com.vmlens.trace.agent.bootstrap.lock.ReadOrWriteLock;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.*;

import static com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.MethodNotFoundAction.NO_OP;
import static com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.MethodNotFoundAction.WARNING_AND_NOT_TRANSFORM;

public class FactoryCollectionPreAnalyzedFactoryBuilderImpl implements FactoryCollectionPreAnalyzedFactoryBuilder {

    private final THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy = new
            THashMap<>();
    private final MethodRepositoryForTransform methodCallIdMap;
    private final PreAnalyzedStrategy preAnalyzedStrategy;
    private MethodNotFoundAction methodNotFoundAction = WARNING_AND_NOT_TRANSFORM;

    public FactoryCollectionPreAnalyzedFactoryBuilderImpl(MethodRepositoryForTransform methodCallIdMap,
                                                          PreAnalyzedStrategy preAnalyzedStrategy) {
        this.methodCallIdMap = methodCallIdMap;
        this.preAnalyzedStrategy = preAnalyzedStrategy;
    }


    @Override
    public void getReadWriteLock(String name, String desc) {
        methodToStrategy.put(new NameAndDescriptor(name, desc), new GetReadWriteLockMethodStrategy());

    }

    @Override
    public void addMethodWithLock(String name, String desc, ReadOrWriteLock lockType) {
        methodToStrategy.put(new NameAndDescriptor(name, desc), new MethodWithLockStrategy(lockType));
    }



    @Override
    public void addNonBlockingMethod(String name, String desc, int operation,
                                     CallbackInNonBlockingMethod[] callbackMethods) {
        NameAndDescriptor nameAndDescriptor = new NameAndDescriptor(name, desc);
        methodToStrategy.put(nameAndDescriptor, new NonBlockingStrategy(operation));
        preAnalyzedStrategy.addCallback(nameAndDescriptor,callbackMethods);
    }

    @Override
    public void addLockMethod(String name, String desc, LockType lockType, LockOperation lockOperation) {
        methodToStrategy.put(new NameAndDescriptor(name, desc), new LockMethodStrategy(lockOperation,lockType));
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
        return new FactoryCollectionPreAnalyzedFactory(methodToStrategy, methodNotFoundAction, methodCallIdMap,preAnalyzedStrategy);
    }


}
