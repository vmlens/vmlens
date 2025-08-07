package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.NotYetImplementedAction;

public class NotYetImplementedStrategy implements StrategyPreAnalyzed {

    private final String className;
    private final String methodName;

    public NotYetImplementedStrategy(String className,
                                     String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        processNotYetImplemented(context);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        processNotYetImplemented(context);
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }

    private void processNotYetImplemented(EnterExitContext context) {
        context.threadLocalWhenInTestAdapter().process(new NotYetImplementedAction(className,methodName));

    }
}
