package com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;

public class DoNotTraceInClass  extends AbstractClassType {

    public static final DoNotTraceInClass SINGLETON = new DoNotTraceInClass();

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addDoNotTraceIn(name);
    }
}
