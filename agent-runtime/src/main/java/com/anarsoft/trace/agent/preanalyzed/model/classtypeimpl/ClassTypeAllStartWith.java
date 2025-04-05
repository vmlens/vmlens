package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;

public class ClassTypeAllStartWith extends AbstractClassType {

    public static final ClassTypeAllStartWith SINGLETON = new ClassTypeAllStartWith();

    private ClassTypeAllStartWith() {
    }

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addTraceStartsWith(name);
    }
}
