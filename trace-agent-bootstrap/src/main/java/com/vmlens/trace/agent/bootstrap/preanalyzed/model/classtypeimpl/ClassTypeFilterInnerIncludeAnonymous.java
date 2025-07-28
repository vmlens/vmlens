package com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;

public class ClassTypeFilterInnerIncludeAnonymous extends AbstractClassType {

    public static final ClassTypeFilterInnerIncludeAnonymous SINGLETON = new ClassTypeFilterInnerIncludeAnonymous();

    private ClassTypeFilterInnerIncludeAnonymous() {
    }

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addFilterInnerIncludeAnonymous(name);
    }
}
