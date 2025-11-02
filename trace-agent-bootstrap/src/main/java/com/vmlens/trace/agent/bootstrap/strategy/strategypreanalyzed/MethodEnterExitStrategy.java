package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.*;

/**
 * when we only need a method enter/exit in a preanalyzed class
 * for example future run
 *
 */

public class MethodEnterExitStrategy extends StrategyWithoutParam {

    @Override
    public void methodEnter(EnterExitContext context) {
        methodEnterEvent(context);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        methodExitEvent(context);
    }

}
