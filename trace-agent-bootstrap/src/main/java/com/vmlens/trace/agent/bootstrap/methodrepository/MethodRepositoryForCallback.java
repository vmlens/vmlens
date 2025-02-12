package com.vmlens.trace.agent.bootstrap.methodrepository;

import com.vmlens.trace.agent.bootstrap.strategy.strategyall.StrategyAll;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public interface MethodRepositoryForCallback {

    StrategyAll strategyAll(int methodId);

    StrategyPreAnalyzed strategyPreAnalyzed(int methodId);

}
