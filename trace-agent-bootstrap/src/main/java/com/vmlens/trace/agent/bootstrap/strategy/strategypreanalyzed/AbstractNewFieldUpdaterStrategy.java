package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;


/*
 * the actual action is done through InitializationAction see PreAnalyzedCallback.methodObjectStringParamObjectReturn
 * I use a strategy so that i can reuse the transformation based on strategy
 *
 */

public abstract class AbstractNewFieldUpdaterStrategy  implements StrategyPreAnalyzed {

    @Override
    public void methodEnter(EnterExitContext context) {
        // Nothing to do
    }

    @Override
    public void methodExit(EnterExitContext context) {
        throw new RuntimeException("should not be called");
    }

}
