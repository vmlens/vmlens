package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;

public class ClassTypeFilter extends AbstractClassType {

    public static final ClassTypeFilter SINGLETON = new ClassTypeFilter();

    private ClassTypeFilter() {
    }

    @Override
    public void add(String name, PreAnalyzedMethod[] methods, ClassBuilder classBuilder) {
        classBuilder.addFilterStartsWith(name);
    }
}
