package com.anarsoft.trace.agent.runtime.applyclasstransformer.builder;

import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategyNoOp;
import com.anarsoft.trace.agent.runtime.classtransformer.TransformerStrategyForClassTransformer;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.factory.FactoryCollectionAllFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.factory.FactoryCollectionFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.factory.FactoryCollectionPreAnalyzedFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.preanalyzedstrategy.NoMethodCall;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.preanalyzedstrategy.WithObjectMethodCall;
import com.anarsoft.trace.agent.runtime.classtransformervmlensapi.TransformerStrategyVmlensApi;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;

public class TransformerStrategyFactory {

    private final MethodRepositoryForTransform methodRepositoryForAnalyze;
    private final FieldRepositoryForTransform fieldRepositoryForAnalyze;
    private final WriteClassDescriptionAndWarning writeClassDescription;


    public TransformerStrategyFactory(MethodRepositoryForTransform methodRepositoryForAnalyze,
                                      FieldRepositoryForTransform fieldRepositoryForAnalyze,
                                      WriteClassDescriptionAndWarning writeClassDescription) {
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
        this.fieldRepositoryForAnalyze = fieldRepositoryForAnalyze;
        this.writeClassDescription = writeClassDescription;
    }

    public TransformerStrategy createNoOp() {
        return new TransformerStrategyNoOp();
    }

    public TransformerStrategy createVmlensApi() {
        return new TransformerStrategyVmlensApi();
    }

    public TransformerStrategy createAll() {
        FactoryCollectionFactory factoryCollectionFactory = new FactoryCollectionAllFactory(fieldRepositoryForAnalyze,
                methodRepositoryForAnalyze);
        return new TransformerStrategyForClassTransformer(factoryCollectionFactory,
                methodRepositoryForAnalyze, fieldRepositoryForAnalyze, writeClassDescription);
    }

    public TransformerStrategy createPreAnalyzed(FactoryCollectionPreAnalyzedFactory factoryCollectionPreAnalyzedBuilder) {
        return new TransformerStrategyForClassTransformer(factoryCollectionPreAnalyzedBuilder,
                methodRepositoryForAnalyze, fieldRepositoryForAnalyze, writeClassDescription);
    }

    public FactoryCollectionPreAnalyzedFactoryBuilder createTraceNoMethodCall() {
        return new FactoryCollectionPreAnalyzedFactoryBuilderImpl(methodRepositoryForAnalyze, new NoMethodCall());
    }

    public FactoryCollectionPreAnalyzedFactoryBuilder createTraceMethodCallWithObject() {
        return new FactoryCollectionPreAnalyzedFactoryBuilderImpl(methodRepositoryForAnalyze, new WithObjectMethodCall());
    }

}
