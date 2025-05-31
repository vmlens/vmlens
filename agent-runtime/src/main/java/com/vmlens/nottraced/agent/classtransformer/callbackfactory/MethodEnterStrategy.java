package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

public interface MethodEnterStrategy {

    void methodEnter(MethodCallbackFactory parent,int inMethodId);

}
