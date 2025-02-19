package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

public class SynchronizedMethodStrategy implements StrategyAll {

    public static final StrategyAll SINGLETON = new SynchronizedMethodStrategy();

    private SynchronizedMethodStrategy() {
    }

    @Override
    public void methodEnter(EnterExitContext enterExitContext) {
    }


}
