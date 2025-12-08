package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CalleeFactory;
import org.objectweb.asm.MethodVisitor;

public abstract class DefaultMethodCallbackFactory extends MethodCallbackFactory {

    public DefaultMethodCallbackFactory(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    @Override
    public void methodExit(int inMethodId, CalleeFactory calleeFactory) {
        calleeFactory.createCallee();
        methodCall(inMethodId, METHOD_EXIT, METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }

    @Override
    public void methodExitWithObjectReturn(int inMethodId, CalleeFactory calleeFactory) {
        methodExit(inMethodId,calleeFactory);
    }
}
