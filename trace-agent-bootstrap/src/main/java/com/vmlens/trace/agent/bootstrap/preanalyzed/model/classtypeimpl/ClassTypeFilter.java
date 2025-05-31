package com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;

public class ClassTypeFilter extends AbstractClassType {

    public static final ClassTypeFilter SINGLETON = new ClassTypeFilter();

    private ClassTypeFilter() {
    }

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addFilterStartsWith(name);
    }
}
