package com.anarsoft.trace.agent.runtime.inttest;

import com.anarsoft.trace.agent.preanalyzed.model.MethodType;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;

public class PreAnalyzedMethodBuilder {

    private final MethodType methodType;
    private String name = "method";

    public PreAnalyzedMethodBuilder(MethodType methodType) {
        this.methodType = methodType;
    }

    public void withName(String name) {
        this.name = name;
    }



    public PreAnalyzedMethod build() {
        return  new PreAnalyzedMethod(name, "()Ljava/lang/Object;",methodType);
    }

}
