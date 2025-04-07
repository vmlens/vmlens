package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.lock.LockType;

public class LockMethodStrategy implements StrategyPreAnalyzed  {

    private final LockOperation lockOperation;
    private final LockType lockType;

    public LockMethodStrategy(LockOperation lockOperation, LockType lockType) {
        this.lockOperation = lockOperation;
        this.lockType = lockType;
    }

    @Override
    public void methodEnter(EnterExitContext context) {

    }

    @Override
    public void methodExit(EnterExitContext context) {

    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {

    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {

    }
}
