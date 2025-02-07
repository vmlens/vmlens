package com.anarsoft.trace.agent.runtime.applyclasstransformer.builder;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategyNoOp;
import com.anarsoft.trace.agent.runtime.classtransformer.ClassTransformerFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.ClassTransformerFactoryAll;
import com.anarsoft.trace.agent.runtime.classtransformer.ClassTransformerFactoryPreAnalyzed;
import com.anarsoft.trace.agent.runtime.classtransformer.TransformerStrategyClassTransformer;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.FactoryCollectionPreAnalyzedFactory;
import com.anarsoft.trace.agent.runtime.classtransformervmlensapi.TransformerStrategyVmlensApi;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodRepositoryForAnalyze;

public class TransformerStrategyFactory {

    private final MethodRepositoryForAnalyze methodRepositoryForAnalyze;
    private final FieldRepositoryForAnalyze fieldRepositoryForAnalyze;
    private final WriteClassDescriptionAndWarning writeClassDescription;

    public TransformerStrategyFactory(MethodRepositoryForAnalyze methodRepositoryForAnalyze,
                                      FieldRepositoryForAnalyze fieldRepositoryForAnalyze,
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
        ClassTransformerFactory classTransformerFactoryAll =
                new ClassTransformerFactoryAll(methodRepositoryForAnalyze,
                        fieldRepositoryForAnalyze,
                        writeClassDescription);


        return new TransformerStrategyClassTransformer(classTransformerFactoryAll);
    }

    public TransformerStrategy createPreAnalyzed(FactoryCollectionPreAnalyzedFactory factoryCollectionPreAnalyzedBuilder) {
        ClassTransformerFactory classTransformerFactoryPreAnalyzed =
                new ClassTransformerFactoryPreAnalyzed(methodRepositoryForAnalyze,
                        fieldRepositoryForAnalyze,
                        writeClassDescription);


        return new TransformerStrategyClassTransformer(classTransformerFactoryPreAnalyzed);
    }
}
