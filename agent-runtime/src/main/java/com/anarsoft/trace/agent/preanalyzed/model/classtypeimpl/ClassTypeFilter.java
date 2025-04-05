package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;

public class ClassTypeFilter extends AbstractClassType {

    public static final ClassTypeFilter SINGLETON = new ClassTypeFilter();

    private ClassTypeFilter() {
    }

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addFilterStartsWith(name);
    }
}
