package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;


import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public class GetReadWriteLockMethodStrategy extends StrategyWithoutParamAndObjectReturn {

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

}
