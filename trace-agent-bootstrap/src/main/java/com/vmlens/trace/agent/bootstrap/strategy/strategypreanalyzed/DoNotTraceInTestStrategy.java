package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.donottrace.StartDoNotTraceAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.donottrace.StopDoNotTraceAction;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public class DoNotTraceInTestStrategy implements StrategyPreAnalyzed {

    @Override
    public void methodEnter(EnterExitContext context) {
        context.inTestActionProcessor().process(new StartDoNotTraceAction());
    }

    @Override
    public void methodExit(EnterExitContext context) {
        context.inTestActionProcessor().process(new StopDoNotTraceAction());
    }
}
