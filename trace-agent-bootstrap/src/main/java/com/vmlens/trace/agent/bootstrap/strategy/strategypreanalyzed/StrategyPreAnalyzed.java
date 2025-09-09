package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public interface StrategyPreAnalyzed {

    void methodEnter(EnterExitContext context);
    void methodExit(EnterExitContext context);


}