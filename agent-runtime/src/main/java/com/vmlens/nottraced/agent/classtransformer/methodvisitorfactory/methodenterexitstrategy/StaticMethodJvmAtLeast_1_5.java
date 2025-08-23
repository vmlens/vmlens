package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class StaticMethodJvmAtLeast_1_5 implements MethodEnterExitStrategy  {
    @Override
    public void createMethodEnter(MethodVisitor methodVisitor,
                                  MethodCallbackFactory methodCallbackFactory,
                                  int methodId,
                                  String className) {
        methodVisitor.visitLdcInsn(Type.getType("L" + className + ";"));
        methodCallbackFactory.methodEnter(methodId);
    }

    @Override
    public void createMethodExit(MethodVisitor methodVisitor,
                                 MethodCallbackFactory methodCallbackFactory,
                                 int methodId,
                                 String className) {
        methodVisitor.visitLdcInsn(Type.getType("L" + className + ";"));
        methodCallbackFactory.methodExit(methodId);
    }

    @Override
    public void createMethodExitWithObjectReturn(MethodVisitor methodVisitor,
                                                 MethodCallbackFactory methodCallbackFactory,
                                                 int methodId,
                                                 String className) {
        // There are currently no cases
        // where we need the return value for
        // static methods
        createMethodExit(methodVisitor,methodCallbackFactory,methodId,className);
    }
}
