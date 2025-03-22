package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

public interface StrategyAll {

    void methodEnter(MethodEnterExitContext enterExitContext);
    void methodExit(MethodEnterExitContext enterExitContext);

}
