package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;


import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class GetReadWriteLockMethodStrategy implements StrategyPreAnalyzed {

    public GetReadWriteLockMethodStrategy() {
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        // Nothing to do
    }

    @Override
    public void methodExit(EnterExitContext context) {
        context.readWriteLockMap().addUnderlying(System.identityHashCode(context.returnValue()),
                System.identityHashCode(context.object()));
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }
}
