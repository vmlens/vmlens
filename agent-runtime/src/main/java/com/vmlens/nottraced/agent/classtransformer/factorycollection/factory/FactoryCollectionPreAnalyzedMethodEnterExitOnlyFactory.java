package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollectionPreAnalyzedMethodEnterExitOnly;

public class FactoryCollectionPreAnalyzedMethodEnterExitOnlyFactory implements FactoryCollectionFactory {


    private final PreAnalyzedStrategyFactory preAnalyzedStrategyFactory;

    public FactoryCollectionPreAnalyzedMethodEnterExitOnlyFactory(PreAnalyzedStrategyFactory preAnalyzedStrategyFactory) {
        this.preAnalyzedStrategyFactory = preAnalyzedStrategyFactory;
    }

    @Override
    public FactoryCollection create() {
        return new FactoryCollectionPreAnalyzedMethodEnterExitOnly(preAnalyzedStrategyFactory);
    }
}
