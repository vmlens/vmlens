package com.vmlens.trace.agent.bootstrap.methodrepository;

import com.vmlens.trace.agent.bootstrap.strategy.strategyall.StrategyAll;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public class EitherEmptyAllOrPreAnalyzed {

    private final int methodId;
    private StrategyAll strategyAll;
    private StrategyPreAnalyzed strategyPreAnalyzed;

    public EitherEmptyAllOrPreAnalyzed(int methodId) {
        this.methodId = methodId;
    }

    public int methodId() {
        return methodId;
    }

    public StrategyAll strategyAll() {
        return strategyAll;
    }

    public void setStrategyAll(StrategyAll strategyAll) {
        this.strategyAll = strategyAll;
    }

    public StrategyPreAnalyzed strategyPreAnalyzed() {
        return strategyPreAnalyzed;
    }

    public void setStrategyPreAnalyzed(StrategyPreAnalyzed strategyPreAnalyzed) {
        this.strategyPreAnalyzed = strategyPreAnalyzed;
    }
}
