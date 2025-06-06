package com.vmlens.nottraced.agent.classtransformer;

import com.vmlens.nottraced.agent.applyclasstransformer.TransformerContext;
import com.vmlens.nottraced.agent.applyclasstransformer.TransformerStrategy;
import com.vmlens.nottraced.agent.classanalyzer.ClassVisitorAnalyze;
import com.vmlens.nottraced.agent.classtransformer.factorycollection.factory.FactoryCollectionFactory;
import com.vmlens.nottraced.agent.write.WriteClassDescriptionAndWarning;
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
