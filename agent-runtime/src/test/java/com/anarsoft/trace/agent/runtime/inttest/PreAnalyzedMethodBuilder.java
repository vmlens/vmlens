package com.anarsoft.trace.agent.runtime.inttest;

import com.anarsoft.trace.agent.preanalyzed.model.MethodType;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.CallbackInNonBlockingMethod;

public class PreAnalyzedMethodBuilder {

    private final MethodType methodType;
    private CallbackInNonBlockingMethod[] callback = new  CallbackInNonBlockingMethod[0];
    private String name = "method";

    public PreAnalyzedMethodBuilder(MethodType methodType) {
        this.methodType = methodType;
    }

    public void withName(String name) {
        this.name = name;
    }

    public void withCallback(CallbackInNonBlockingMethod[] callback) {
        this.callback = callback;
    }

    public PreAnalyzedMethod build() {
        return  new PreAnalyzedMethod(name, "()Ljava/lang/Object;",methodType, callback);
    }

}
