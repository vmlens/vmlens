package com.vmlens.trace.agent.bootstrap.methodrepository;

import com.vmlens.trace.agent.bootstrap.strategy.strategyall.StrategyAll;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public interface EitherAllOrPreAnalyzed {

     StrategyAll strategyAll();

     StrategyPreAnalyzed strategyPreAnalyzed();

}
