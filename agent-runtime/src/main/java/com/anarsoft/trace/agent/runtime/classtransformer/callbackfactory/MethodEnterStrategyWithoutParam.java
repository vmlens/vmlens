package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

import static com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MethodCallbackFactory.METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT;

public class MethodEnterStrategyWithoutParam implements MethodEnterStrategy {

    private final String METHOD_ENTER = "methodEnter";

    @Override
    public void methodEnter(MethodCallbackFactory parent, int inMethodId) {
        parent.methodCall(inMethodId, METHOD_ENTER, METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }
}
