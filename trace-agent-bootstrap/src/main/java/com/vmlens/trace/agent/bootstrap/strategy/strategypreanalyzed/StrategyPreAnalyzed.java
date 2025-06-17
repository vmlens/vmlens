package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

public interface StrategyPreAnalyzed {

    void methodEnter(EnterExitContext context);

    void methodExit(EnterExitContext context);

    void beforeMethodCall(BeforeAfterContext beforeAfterContext);

    void afterMethodCall(BeforeAfterContext beforeAfterContext);

}