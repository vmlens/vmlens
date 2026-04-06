package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.initialize;


import com.vmlens.trace.agent.bootstrap.callback.callbackaction.initializationaction.StrategyInitializeContext;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyWithoutParamAndObjectReturn;

public class GetReadWriteLockMethodStrategy extends AbstractStrategyInitialize {

    public GetReadWriteLockMethodStrategy() {
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        throw new RuntimeException("should not be called");
    }

    @Override
    public void methodExit(EnterExitContext context) {
        throw new RuntimeException("should not be called");
    }

    @Override
    public void intitialize(StrategyInitializeContext context) {
        context.readWriteLockMap().addUnderlying(System.identityHashCode(context.returnValue()),
                System.identityHashCode(context.object()));
    }
}
