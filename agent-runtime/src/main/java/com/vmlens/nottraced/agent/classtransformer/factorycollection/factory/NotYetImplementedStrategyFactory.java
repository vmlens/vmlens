package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.transformed.agent.bootstrap.strategy.strategypreanalyzed.NotYetImplementedStrategy;
import com.vmlens.transformed.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public class NotYetImplementedStrategyFactory implements PreAnalyzedStrategyFactory {
    @Override
    public StrategyPreAnalyzed create(String className, NameAndDescriptor nameAndDescriptor) {
        return new NotYetImplementedStrategy();
    }
}
