package com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;

public class DoNotTraceInTestContainsClassName extends AbstractClassType {

    public static final DoNotTraceInTestContainsClassName SINGLETON = new DoNotTraceInTestContainsClassName();

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addDoNotTraceInTestContainsClassName(name);
    }
}    
