package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.*;

/**
 * when we only need a method enter/exit in a preanalyzed class
 * for example future run
 *
 */

public class MethodEnterExitStrategy implements StrategyPreAnalyzed {

    @Override
    public void methodEnter(EnterExitContext context) {
        methodEnterEvent(context);
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
