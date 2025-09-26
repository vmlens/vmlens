package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.DoNotTraceInTestStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public class DoNotTraceInTestStrategyFactory implements PreAnalyzedStrategyFactory {
    @Override
    public StrategyPreAnalyzed create(String className, NameAndDescriptor nameAndDescriptor) {
        return new DoNotTraceInTestStrategy();
    }
}
