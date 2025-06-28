package com.vmlens.trace.agent.bootstrap.preanalyzed.builder;


import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public interface FactoryCollectionPreAnalyzedFactoryBuilder {

    void addNonBlockingArrayMethod(String name, String desc, int operation);

    void addPreAnalyzedMethod(String name, String desc, StrategyPreAnalyzed strategyPreAnalyzed);

    void noOpWhenMethodNotFound();

    void setThreadPoolStart(String name, String desc);

    void addThreadPoolJoin(String name, String desc);

}
