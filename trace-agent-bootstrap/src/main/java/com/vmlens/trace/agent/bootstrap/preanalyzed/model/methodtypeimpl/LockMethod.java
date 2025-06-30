package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.lock.LockEnter;
import com.vmlens.trace.agent.bootstrap.lock.LockTypes;
import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.LockEnterStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.LockExitStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.NewConditionStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public class LockMethod  extends AbstractMethodType  {

    public static final LockMethod ENTER_REENTRANT_LOCK = new LockMethod(new LockEnterStrategy(new LockEnter(), LockTypes.REENTRANT_LOCK));
    public static final LockMethod EXIT_REENTRANT_LOCK = new LockMethod(new LockExitStrategy(LockTypes.REENTRANT_LOCK));

    public static final LockMethod ENTER_READ_LOCK = new LockMethod(new LockEnterStrategy(new LockEnter(), LockTypes.READ_LOCK));
    public static final LockMethod EXIT_READ_LOCK = new LockMethod(new LockExitStrategy(LockTypes.READ_LOCK));

    public static final LockMethod ENTER_WRITE_LOCK = new LockMethod(new LockEnterStrategy(new LockEnter(), LockTypes.WRITE_LOCK));
    public static final LockMethod EXIT_WRITE_LOCK = new LockMethod(new LockExitStrategy(LockTypes.WRITE_LOCK));

    public static final LockMethod NEW_CONDITION = new LockMethod(new NewConditionStrategy());

    private final StrategyPreAnalyzed strategyPreAnalyzed;

    private LockMethod(StrategyPreAnalyzed strategyPreAnalyzed) {
        this.strategyPreAnalyzed = strategyPreAnalyzed;
    }

    @Override
    public void add(String name, String desc,FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addPreAnalyzedMethod(name,desc,strategyPreAnalyzed);
    }
}
