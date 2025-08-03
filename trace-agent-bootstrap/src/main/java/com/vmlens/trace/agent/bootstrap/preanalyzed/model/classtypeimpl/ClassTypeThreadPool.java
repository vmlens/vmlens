package com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.ClassType;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;

public class ClassTypeThreadPool extends AbstractClassType {

    public static final ClassType SINGLETON = new ClassTypeThreadPool();

    private ClassTypeThreadPool() {
    }

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder = classBuilder.createTraceNoMethodCall();
        for(PreAnalyzedMethod m : methods) {
            m.add(name,methodBuilder);
        }
        classBuilder.addThreadPool(name,methodBuilder);
    }
}
