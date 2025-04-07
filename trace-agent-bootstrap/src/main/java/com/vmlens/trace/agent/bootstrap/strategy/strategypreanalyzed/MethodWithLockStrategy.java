package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.lock.ReadOrWriteLock;

public class MethodWithLockStrategy implements StrategyPreAnalyzed {

    private final ReadOrWriteLock lockType;

    public MethodWithLockStrategy(ReadOrWriteLock lockType) {
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
