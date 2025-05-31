package com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;

public class ClassTypeAllStartWith extends AbstractClassType {

    public static final ClassTypeAllStartWith SINGLETON = new ClassTypeAllStartWith();

    private ClassTypeAllStartWith() {
    }

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addTraceStartsWith(name);
    }
}
