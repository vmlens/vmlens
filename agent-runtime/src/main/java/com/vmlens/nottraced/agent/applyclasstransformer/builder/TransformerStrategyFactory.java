package com.vmlens.nottraced.agent.applyclasstransformer.builder;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.nottraced.agent.applyclasstransformer.TransformerStrategy;
import com.vmlens.nottraced.agent.applyclasstransformer.TransformerStrategyNoOp;
import com.vmlens.nottraced.agent.classtransformer.TransformerStrategyForClassTransformer;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.factory.*;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.preanalyzedstrategy.SelectMethodEnterStrategy;
import com.vmlens.nottraced.agent.classtransformervmlensapi.TransformerStrategyVmlensApi;
import com.vmlens.nottraced.agent.write.WriteClassDescriptionAndWarning;
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

    public TransformerStrategy createMethodEnterExitOnly(PreAnalyzedStrategyFactory preAnalyzedStrategyFactory) {
        return new TransformerStrategyForClassTransformer(
                new FactoryCollectionPreAnalyzedMethodEnterExitOnlyFactory(methodRepositoryForAnalyze,
                        preAnalyzedStrategyFactory),
                methodRepositoryForAnalyze, fieldRepositoryForAnalyze, writeClassDescription);
    }

    public TransformerStrategy createAll() {
        FactoryCollectionFactory factoryCollectionFactory = new FactoryCollectionAllFactory(fieldRepositoryForAnalyze,
                methodRepositoryForAnalyze);
        return new TransformerStrategyForClassTransformer(factoryCollectionFactory,
                methodRepositoryForAnalyze, fieldRepositoryForAnalyze, writeClassDescription);
    }

    public TransformerStrategy create(FactoryCollectionFactory factoryCollectionPreAnalyzedBuilder) {
        return new TransformerStrategyForClassTransformer(factoryCollectionPreAnalyzedBuilder,
                methodRepositoryForAnalyze, fieldRepositoryForAnalyze, writeClassDescription);
    }

    public FactoryCollectionPreAnalyzedFactoryBuilder createTraceNoMethodCall() {
        return new FactoryCollectionPreAnalyzedFactoryBuilderImpl(methodRepositoryForAnalyze, new SelectMethodEnterStrategy());
    }

    public TransformerStrategy createDoNotTraceIn() {
        return new TransformerStrategyForClassTransformer(new FactoryCollectionDoNotTraceFactory(methodRepositoryForAnalyze),
                methodRepositoryForAnalyze, fieldRepositoryForAnalyze, writeClassDescription);
    }

}
