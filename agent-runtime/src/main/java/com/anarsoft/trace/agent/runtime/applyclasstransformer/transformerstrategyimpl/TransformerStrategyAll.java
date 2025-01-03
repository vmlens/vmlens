package com.anarsoft.trace.agent.runtime.applyclasstransformer.transformerstrategyimpl;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerContext;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import com.anarsoft.trace.agent.runtime.classanalyzer.ClassVisitorAnalyze;
import com.anarsoft.trace.agent.runtime.classtransformerall.ClassTransformer;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForAnalyze;
import org.objectweb.asm.ClassVisitor;

public class TransformerStrategyAll implements TransformerStrategy {

    private final MethodRepositoryForAnalyze methodRepositoryForAnalyze;
    private final FieldRepositoryForAnalyze fieldRepositoryForAnalyze;
    private final WriteClassDescriptionAndWarning writeClassDescription;

    public TransformerStrategyAll(MethodRepositoryForAnalyze methodRepositoryForAnalyze,
                                  FieldRepositoryForAnalyze fieldRepositoryForAnalyze,
                                  WriteClassDescriptionAndWarning writeClassDescription) {
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
        this.fieldRepositoryForAnalyze = fieldRepositoryForAnalyze;
        this.writeClassDescription = writeClassDescription;
    }

    @Override
    public byte[] transform(TransformerContext context) {
        ClassVisitor classVisitor = new ClassVisitorAnalyze(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze,
                writeClassDescription);
        ClassTransformer classTransformer = ClassTransformer.createAll(methodRepositoryForAnalyze,
                fieldRepositoryForAnalyze,
                classVisitor);
        return classTransformer.transform(context.classfileBuffer(), context.name());
    }
}
