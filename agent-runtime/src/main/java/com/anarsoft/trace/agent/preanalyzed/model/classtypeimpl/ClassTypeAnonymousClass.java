package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;

public class ClassTypeAnonymousClass extends AbstractClassType {

    public static final ClassTypeAnonymousClass SINGLETON = new ClassTypeAnonymousClass();

    private ClassTypeAnonymousClass() {
    }

    @Override
    public void add(String name, PreAnalyzedMethod[] methods, ClassBuilder classBuilder) {
        classBuilder.createTraceAnonymousClass(name);
    }
}
