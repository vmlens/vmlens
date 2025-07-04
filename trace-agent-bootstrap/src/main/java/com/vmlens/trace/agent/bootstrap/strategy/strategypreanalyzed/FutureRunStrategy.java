package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.methodExitEvent;
import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.newTask;

/**
 *
 * used for FutureTask and similar classes.
 * So all pre analyzed classes which implement runnable
 *
 */

public class FutureRunStrategy implements StrategyPreAnalyzed {

    @Override
    public void methodEnter(EnterExitContext context) {
        newTask(context);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        methodExitEvent(context);
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {

    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {

    }
}
