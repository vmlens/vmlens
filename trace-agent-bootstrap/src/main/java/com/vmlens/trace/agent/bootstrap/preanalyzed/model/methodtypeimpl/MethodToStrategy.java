package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.barrierkeytype.BarrierKeyTypeFuture;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodTypeContext;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.*;

public class MethodToStrategy extends AbstractMethodType  {

    public static final AbstractMethodType CONDITION_AWAIT = new MethodToStrategy(new ConditionAWaitStrategy());


    public static final AbstractMethodType FUTURE_GET = new MethodToStrategy(new BarrierWaitStrategy(BarrierKeyTypeFuture.SINGLETON));
    public static final AbstractMethodType FUTURE_SET = new MethodToStrategy(new BarrierNotifyStrategy(
            BarrierKeyTypeFuture.SINGLETON));
    public static final AbstractMethodType METHOD_ENTER_EXIT = new MethodToStrategy(new MethodEnterExitStrategy());




    public static final AbstractMethodType REFLECT_FIELD_GET = new MethodToStrategy(ReflectionFieldStrategy.REFLECT_FIELD_READ);
    public static final AbstractMethodType REFLECT_FIELD_SET = new MethodToStrategy(ReflectionFieldStrategy.REFLECT_FIELD_WRITE);

    public static final AbstractMethodType NEW_UPDATER = new MethodToStrategy(NewAtomicIntegerFieldUpdaterStrategy.SINGLETON);
    public static final AbstractMethodType NEW_REFERENCE_UPDATER = new MethodToStrategy(NewAtomicReferenceFieldUpdaterStrategy.SINGLETON);



    public static final AbstractMethodType ATOMIC_FIELD_READ = new MethodToStrategy(AtomicFieldUpdaterStrategy.ATOMIC_FIELD_READ);
    public static final AbstractMethodType ATOMIC_FIELD_WRITE = new MethodToStrategy(AtomicFieldUpdaterStrategy.ATOMIC_FIELD_WRITE);
    public static final AbstractMethodType ATOMIC_FIELD_READ_WRITE = new MethodToStrategy(AtomicFieldUpdaterStrategy.ATOMIC_FIELD_READ_WRITE);
    
    private final StrategyPreAnalyzed strategyPreAnalyzed;

    private MethodToStrategy(StrategyPreAnalyzed strategyPreAnalyzed) {
        this.strategyPreAnalyzed = strategyPreAnalyzed;
    }

    @Override
    public void add(MethodTypeContext context) {
        context.methodBuilder().addPreAnalyzedMethod(context.name(),context.desc(),strategyPreAnalyzed);
    }
}
