package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassBuilder;
import com.anarsoft.trace.agent.preanalyzed.builder.MethodBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.ClassType;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;

public class ClassTypeThread extends AbstractClassType {

    public static final ClassType SINGLETON = new ClassTypeThread();

    private ClassTypeThread() {
    }

    @Override
    public void add(String name,
                    PreAnalyzedMethod[] methods,
                    ClassBuilder classBuilder) {
        MethodBuilder methodBuilder = classBuilder.createThread(name);
        for (PreAnalyzedMethod method : methods) {
            method.add(methodBuilder);
        }
        methodBuilder.doNothingWhenNotFound();
    }
}
