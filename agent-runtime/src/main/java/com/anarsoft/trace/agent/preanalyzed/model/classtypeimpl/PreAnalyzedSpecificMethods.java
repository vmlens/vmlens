package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassBuilder;
import com.anarsoft.trace.agent.preanalyzed.builder.MethodBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.ClassType;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;

public class PreAnalyzedSpecificMethods extends AbstractClassType {

    public static final ClassType SINGLETON = new PreAnalyzedSpecificMethods();

    private PreAnalyzedSpecificMethods() {
    }

    @Override
    public void addToBuilder(String name,
                             PreAnalyzedMethod[] methods,
                             ClassBuilder classBuilder) {
        MethodBuilder methodBuilder = classBuilder.createMethodBuilder();
        for (PreAnalyzedMethod method : methods) {
            method.add(methodBuilder);
        }
        methodBuilder.noOpWhenMethodNotFound();
        classBuilder.addPreAnalyzedEquals(name, methodBuilder.build());
    }
}
