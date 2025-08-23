package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import org.objectweb.asm.MethodVisitor;

public interface MethodEnterExitStrategy {

    void createMethodEnter(MethodVisitor methodVisitor,MethodCallbackFactory methodCallbackFactory, int methodId, String className);
    void createMethodExit(MethodVisitor methodVisitor,MethodCallbackFactory methodCallbackFactory, int methodId, String className);
    void createMethodExitWithObjectReturn(MethodVisitor methodVisitor,MethodCallbackFactory methodCallbackFactory, int methodId, String className);

}
