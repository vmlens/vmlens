package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.ThrowNotYetImplementedException;

public class NotYetImplementedStrategy implements StrategyPreAnalyzed {

    private final String methodName;

    public NotYetImplementedStrategy(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        throwException(context);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        throwException(context);
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }

    private void throwException(EnterExitContext context) {
        context.threadLocalWhenInTestAdapter().process(new ThrowNotYetImplementedException(methodName));
    }
}
