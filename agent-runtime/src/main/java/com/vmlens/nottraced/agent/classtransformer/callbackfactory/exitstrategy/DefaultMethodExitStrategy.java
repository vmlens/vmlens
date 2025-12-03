package com.vmlens.nottraced.agent.classtransformer.callbackfactory.exitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CalleeFactory;

import static com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory.METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT;
import static com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory.METHOD_EXIT;

public class DefaultMethodExitStrategy extends MethodExitWithoutObjectReturnStrategy{

    @Override
    public void methodExit(MethodCallbackFactoryPreAnalyzed parent, int inMethodId, CalleeFactory calleeFactory) {
        calleeFactory.createCallee();
        parent.methodCall(inMethodId, METHOD_EXIT, METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }
}
