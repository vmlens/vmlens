package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;

public class ClassTypeVmlensApi extends AbstractClassType {

    public static final ClassTypeVmlensApi SINGLETON = new ClassTypeVmlensApi();
    @Override
    public void addToBuilder(String name, PreAnalyzedMethod[] methods, ClassTransformerListBuilder classBuilder) {
        classBuilder.addVmlensApi(name);
    }
}
