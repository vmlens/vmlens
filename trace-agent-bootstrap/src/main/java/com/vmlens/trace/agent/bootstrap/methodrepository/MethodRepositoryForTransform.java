package com.vmlens.trace.agent.bootstrap.methodrepository;

import com.vmlens.trace.agent.bootstrap.strategy.strategyall.StrategyAll;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;


public interface MethodRepositoryForTransform {

    int asInt(MethodCallId methodCallId);

    void setStrategyAll(int id, StrategyAll strategyAll);

    void setStrategyPreAnalyzed(int id, StrategyPreAnalyzed strategyPreAnalyzed);



}
