package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

public class ThreadStartStrategy implements StrategyPreAnalyzed {

    public static final StrategyPreAnalyzed SINGLETON = new ThreadStartStrategy();

    private ThreadStartStrategy() {
    }
}
