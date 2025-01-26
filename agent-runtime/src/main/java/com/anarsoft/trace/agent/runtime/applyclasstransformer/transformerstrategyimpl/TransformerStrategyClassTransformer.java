package com.anarsoft.trace.agent.runtime.applyclasstransformer.transformerstrategyimpl;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerContext;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import com.anarsoft.trace.agent.runtime.classanalyzer.ClassVisitorAnalyze;
import com.anarsoft.trace.agent.runtime.classtransformer.ClassTransformer;
import com.anarsoft.trace.agent.runtime.classtransformer.ClassTransformerFactory;
import com.anarsoft.trace.agent.runtime.write.WriteClassDescriptionAndWarning;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForAnalyze;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForAnalyze;
import org.objectweb.asm.ClassVisitor;

public class TransformerStrategyClassTransformer implements TransformerStrategy {

    private final ClassTransformerFactory classTransformerFactory;

    public TransformerStrategyClassTransformer(ClassTransformerFactory classTransformerFactory) {
        this.classTransformerFactory = classTransformerFactory;
    }

    @Override
    public byte[] transform(TransformerContext context) {
        ClassTransformer classTransformer = classTransformerFactory.create();
        return classTransformer.transform(context.classfileBuffer(), context.name());
    }
}
