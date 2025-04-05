package com.anarsoft.trace.agent.runtime.applyclasstransformer.builder;

import com.anarsoft.trace.agent.preanalyzed.builder.CallbackInNonBlockingMethod;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionPreAnalyzedFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.MethodNotFoundAction;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.lock.LockType;
import com.vmlens.trace.agent.bootstrap.lock.ReadOrWriteLock;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.ThreadJoinStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.ThreadStartStrategy;

import static com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.MethodNotFoundAction.NO_OP;
import static com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.MethodNotFoundAction.WARNING_AND_TRANSFORM;

public class FactoryCollectionPreAnalyzedFactoryBuilderImpl implements FactoryCollectionPreAnalyzedFactoryBuilder {

    private final THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy = new
            THashMap<>();
    private final MethodRepositoryForTransform methodCallIdMap;
    private MethodNotFoundAction methodNotFoundAction = WARNING_AND_TRANSFORM;

    public FactoryCollectionPreAnalyzedFactoryBuilderImpl(MethodRepositoryForTransform methodCallIdMap) {
        this.methodCallIdMap = methodCallIdMap;
    }


    @Override
    public void getReadWriteLock(String name, String desc, ReadOrWriteLock lockType) {

    }

    @Override
    public void addMethodWithLock(String name, String desc, ReadOrWriteLock lockType) {

    }

    @Override
    public void addNonBlockingMethod(String name, String desc, int operation,
                                     CallbackInNonBlockingMethod[] callbackMethods) {

    }

    @Override
    public void addLockMethod(String name, String desc, LockType lockType, LockOperation lockOperation) {

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
