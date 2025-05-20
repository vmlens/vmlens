package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;

public class DoNotTraceInClass  extends AbstractClassType {

    public static final DoNotTraceInClass SINGLETON = new DoNotTraceInClass();

    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addDoNotTraceIn(name);
    }
}
