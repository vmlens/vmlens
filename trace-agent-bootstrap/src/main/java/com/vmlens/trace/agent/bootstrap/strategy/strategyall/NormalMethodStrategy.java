package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.*;

public class NormalMethodStrategy implements StrategyAll {

    public static final StrategyAll SINGLETON = new NormalMethodStrategy();

    private NormalMethodStrategy() {
    }

    @Override
    public void methodEnter(MethodEnterExitContext enterExitContext) {
        if(enterExitContext.isThreadRun()) {
            newTask(enterExitContext);
        }
        methodEnterEvent(enterExitContext);
    }

    @Override
    public void methodExit(MethodEnterExitContext enterExitContext) {
        methodExitEvent(enterExitContext);
    }
}
