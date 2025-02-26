package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerContext;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import com.anarsoft.trace.agent.runtime.classanalyzer.ClassVisitorAnalyze;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionFactory;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;

public class TransformerStrategyForClassTransformer implements TransformerStrategy {

    private final FactoryCollectionFactory factoryCollectionFactory;
    private final MethodRepositoryForTransform methodRepositoryForAnalyze;
    private final FieldRepositoryForTransform fieldRepositoryForAnalyze;
    private final WriteClassDescriptionAndWarning writeClassDescription;

    public TransformerStrategyForClassTransformer(FactoryCollectionFactory factoryCollectionFactory,
                                                  MethodRepositoryForTransform methodRepositoryForAnalyze,
                                                  FieldRepositoryForTransform fieldRepositoryForAnalyze,
                                                  WriteClassDescriptionAndWarning writeClassDescription) {
        this.factoryCollectionFactory = factoryCollectionFactory;
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
        this.fieldRepositoryForAnalyze = fieldRepositoryForAnalyze;
        this.writeClassDescription = writeClassDescription;
    }

    @Override
    public byte[] transform(TransformerContext context) {
        ClassVisitorAnalyze classVisitorAnalyze = new ClassVisitorAnalyze(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze,
                writeClassDescription);
        ClassTransformer classTransformer = new ClassTransformer(factoryCollectionFactory.create(),
                methodRepositoryForAnalyze, classVisitorAnalyze);
        return classTransformer.transform(context.classfileBuffer(), context.name());
    }
}
