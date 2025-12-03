package com.vmlens.nottraced.agent.classtransformer.callbackfactory.enterstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;

public interface MethodEnterStrategy {

    void methodEnter(MethodCallbackFactory parent, int inMethodId);

}
