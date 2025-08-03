package com.vmlens.trace.agent.bootstrap.preanalyzed.model;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;

public class MethodTypeContext {

    private final String className;
    private final String name;
    private final String desc;
    private final FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder;

    public String className() {
        return className;
    }

    public MethodTypeContext(String className,
                             String name,
                             String desc,
                             FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        this.className = className;
        this.name = name;
        this.desc = desc;
        this.methodBuilder = methodBuilder;
    }

    public String name() {
        return name;
    }

    public String desc() {
        return desc;
    }

    public FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder() {
        return methodBuilder;
    }
}
