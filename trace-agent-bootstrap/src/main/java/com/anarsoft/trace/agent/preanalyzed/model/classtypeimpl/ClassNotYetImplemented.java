package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;

public class ClassNotYetImplemented extends AbstractClassType {

    public static final ClassNotYetImplemented SINGLETON = new ClassNotYetImplemented();

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addClassNotYetImplemented(name);
    }
}
