package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.NotYetImplementedAction;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public class NotYetImplementedStrategy implements StrategyPreAnalyzed {

    private final String className;
    private final String methodName;

    public NotYetImplementedStrategy(String className,
                                     String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        processNotYetImplemented(context);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        processNotYetImplemented(context);
    }

    private void processNotYetImplemented(EnterExitContext context) {
        context.inTestActionProcessor().process(new NotYetImplementedAction(className,methodName));

    }
}
