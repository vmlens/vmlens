package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerContext;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.TransformerStrategy;
import com.anarsoft.trace.agent.runtime.classtransformer.methodfilter.MethodFilterTakeAll;

public class TransformerStrategyClassTransformer implements TransformerStrategy {

    private final ClassTransformerFactory classTransformerFactory;

    public TransformerStrategyClassTransformer(ClassTransformerFactory classTransformerFactory) {
        this.classTransformerFactory = classTransformerFactory;
    }

    @Override
    public byte[] transform(TransformerContext context) {
        ClassTransformer classTransformer = classTransformerFactory.create(new MethodFilterTakeAll());
        return classTransformer.transform(context.classfileBuffer(), context.name());
    }
}
