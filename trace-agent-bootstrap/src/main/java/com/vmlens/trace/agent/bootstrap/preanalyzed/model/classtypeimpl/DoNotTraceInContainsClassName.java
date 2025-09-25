package com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;

public class DoNotTraceInContainsClassName  extends AbstractClassType {

    public static final DoNotTraceInContainsClassName SINGLETON = new DoNotTraceInContainsClassName();

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addDoNotTraceInContainsClassName(name);
    }
}    
