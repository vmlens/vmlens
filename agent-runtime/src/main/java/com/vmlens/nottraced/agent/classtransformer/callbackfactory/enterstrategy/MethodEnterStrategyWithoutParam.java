package com.vmlens.nottraced.agent.classtransformer.callbackfactory.enterstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;

import static com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory.METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT;
import static com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory.METHOD_ENTER;

public class MethodEnterStrategyWithoutParam implements MethodEnterStrategy {

    @Override
    public void methodEnter(MethodCallbackFactory parent, int inMethodId) {
        parent.methodCall(inMethodId, METHOD_ENTER, METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }
}
