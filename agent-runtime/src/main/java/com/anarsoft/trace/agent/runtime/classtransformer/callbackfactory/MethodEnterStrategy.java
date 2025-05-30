package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

public interface MethodEnterStrategy {

    void methodEnter(MethodCallbackFactory parent,int inMethodId);

}
