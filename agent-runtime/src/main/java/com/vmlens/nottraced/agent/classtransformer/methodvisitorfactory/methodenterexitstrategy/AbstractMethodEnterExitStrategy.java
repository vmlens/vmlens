package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import org.objectweb.asm.MethodVisitor;

public abstract class AbstractMethodEnterExitStrategy implements MethodEnterExitStrategy {

    @Override
    public void createMethodEnter(MethodVisitor methodVisitor, MethodCallbackFactory methodCallbackFactory, int methodId, String className) {
        methodCallbackFactory.methodEnter(methodId, createCalleeFactory(methodVisitor,className));
    }

    @Override
    public void createMethodExit(MethodVisitor methodVisitor, MethodCallbackFactory methodCallbackFactory, int methodId, String className) {
        methodCallbackFactory.methodExit(methodId, createCalleeFactory(methodVisitor,className));
    }

    @Override
    public void createMethodExitWithObjectReturn(MethodVisitor methodVisitor, MethodCallbackFactory methodCallbackFactory, int methodId, String className) {
        methodCallbackFactory.methodExitWithObjectReturn(methodId, createCalleeFactory(methodVisitor,className));
    }

    protected abstract CalleeFactory createCalleeFactory(MethodVisitor methodVisitor, String className);
}
