package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

public interface PreAnalyzedStrategyFactory {

    StrategyPreAnalyzed create(String className, NameAndDescriptor nameAndDescriptor);

}
