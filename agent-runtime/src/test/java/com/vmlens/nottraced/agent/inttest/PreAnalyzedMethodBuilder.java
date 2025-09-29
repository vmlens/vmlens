package com.vmlens.nottraced.agent.inttest;

import com.vmlens.transformed.agent.bootstrap.preanalyzed.model.MethodType;
import com.vmlens.transformed.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;

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
