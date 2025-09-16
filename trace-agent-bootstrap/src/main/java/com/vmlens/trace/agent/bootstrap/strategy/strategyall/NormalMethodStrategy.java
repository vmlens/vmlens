package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.strategy.MethodContext;

import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.methodEnterEvent;
import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.methodExitEvent;

public class NormalMethodStrategy implements StrategyAll {

    public static final StrategyAll SINGLETON = new NormalMethodStrategy();

    private NormalMethodStrategy() {
    }

    @Override
    public void methodEnter(MethodContext enterExitContext) {
        methodEnterEvent(enterExitContext);
    }

    @Override
    public void methodExit(MethodContext enterExitContext) {
        methodExitEvent(enterExitContext);
    }
}
