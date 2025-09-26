package com.vmlens.nottraced.agent.classtransformer.factorycollection.factory;

import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollection;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.FactoryCollectionPreAnalyzedMethodEnterExitOnly;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;

public class FactoryCollectionPreAnalyzedMethodEnterExitOnlyFactory implements FactoryCollectionFactory {

    private final MethodRepositoryForTransform methodRepositoryForAnalyze;
    private final PreAnalyzedStrategyFactory preAnalyzedStrategyFactory;

    public FactoryCollectionPreAnalyzedMethodEnterExitOnlyFactory(MethodRepositoryForTransform methodRepositoryForAnalyze,
                                                                  PreAnalyzedStrategyFactory preAnalyzedStrategyFactory) {
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
        this.preAnalyzedStrategyFactory = preAnalyzedStrategyFactory;
    }

    @Override
    public FactoryCollection create() {
        return new FactoryCollectionPreAnalyzedMethodEnterExitOnly(methodRepositoryForAnalyze,preAnalyzedStrategyFactory);
    }
}
