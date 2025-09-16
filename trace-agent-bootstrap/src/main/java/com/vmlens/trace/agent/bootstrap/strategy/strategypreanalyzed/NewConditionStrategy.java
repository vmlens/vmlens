package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public class NewConditionStrategy implements StrategyPreAnalyzed {

    public NewConditionStrategy() {
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        // Nothing to do
    }

    @Override
    public void methodExit(EnterExitContext context) {
        // for read locks the condition is null
        if(context.returnValue() == null) {
            return;
        }
        context.readWriteLockMap().addCondition(System.identityHashCode(context.returnValue()),
                System.identityHashCode(context.object()));
    }

}
