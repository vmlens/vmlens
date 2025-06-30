package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

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

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }
}
