package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.strategy.MethodContext;

public interface StrategyAll {

    void methodEnter(MethodContext enterExitContext);
    void methodExit(MethodContext enterExitContext);

}
