package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.initialize;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.initializationaction.StrategyInitializeContext;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public class NewConditionStrategy extends AbstractStrategyInitialize {

    public NewConditionStrategy() {
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
        // for read locks the condition is null
        if(context.returnValue() == null) {
            return;
        }
        context.readWriteLockMap().addCondition(System.identityHashCode(context.returnValue()),
                System.identityHashCode(context.object()));
    }
}
