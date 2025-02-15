package com.vmlens.trace.agent.bootstrap.methodrepository;

import com.vmlens.trace.agent.bootstrap.strategy.strategyall.StrategyAll;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public class EitherPreAnalyzed implements EitherAllOrPreAnalyzed {

    private StrategyPreAnalyzed strategyPreAnalyzed;

    public EitherPreAnalyzed(StrategyPreAnalyzed strategyPreAnalyzed) {
        this.strategyPreAnalyzed = strategyPreAnalyzed;
    }

    @Override
    public StrategyAll strategyAll() {
        throw new RuntimeException("EitherPreAnalyzed called for strategyAll");
    }

    public StrategyPreAnalyzed strategyPreAnalyzed() {
        return strategyPreAnalyzed;
    }
}
