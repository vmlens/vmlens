package com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;

public class DoNotTraceInTestStartsWithClassName extends AbstractClassType {

    public static final DoNotTraceInTestStartsWithClassName SINGLETON = new DoNotTraceInTestStartsWithClassName();

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addDoNotTraceInTestStartWithClassName(name);
    }
}
