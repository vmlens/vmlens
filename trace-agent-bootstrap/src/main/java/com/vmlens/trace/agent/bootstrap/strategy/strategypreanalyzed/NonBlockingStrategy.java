package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

public class NonBlockingStrategy implements StrategyPreAnalyzed {

    private final int operation;

    public NonBlockingStrategy(int operation) {
        this.operation = operation;
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
