package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

public class NotYetImplementedStrategy implements StrategyPreAnalyzed {

    private final String methodName;

    public NotYetImplementedStrategy(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        throwException();
    }

    @Override
    public void methodExit(EnterExitContext context) {
        throwException();
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }

    private void throwException() {
        throw new UnsupportedOperationException("The method " + methodName + " can currently not be tested with vmlens.");

    }
}
