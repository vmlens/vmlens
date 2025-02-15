package com.vmlens.trace.agent.bootstrap.methodrepository;

import com.vmlens.trace.agent.bootstrap.strategy.strategyall.StrategyAll;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public class EitherAll implements EitherAllOrPreAnalyzed {

    private final StrategyAll strategyAll;

    public EitherAll(StrategyAll strategyAll) {
        this.strategyAll = strategyAll;
    }

    public StrategyAll strategyAll() {
        return strategyAll;
    }

    @Override
    public StrategyPreAnalyzed strategyPreAnalyzed() {
        throw new RuntimeException("EitherAll called for strategyPreAnalyzed");
    }
}
