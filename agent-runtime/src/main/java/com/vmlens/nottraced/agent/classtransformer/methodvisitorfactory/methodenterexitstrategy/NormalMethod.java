package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.DUP;

public class NormalMethod implements MethodEnterExitStrategy {
    @Override
    public void createMethodEnter(MethodVisitor methodVisitor,
                                  MethodCallbackFactory methodCallbackFactory,
                                  int methodId,
                                  String className) {

        methodVisitor.visitVarInsn(ALOAD, 0);
        methodCallbackFactory.methodEnter(methodId);
    }

    @Override
    public void createMethodExit(MethodVisitor methodVisitor,
                                 MethodCallbackFactory methodCallbackFactory,
                                 int methodId,
                                 String className) {
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodCallbackFactory.methodExit(methodId);
    }

    @Override
    public void createMethodExitWithObjectReturn(MethodVisitor methodVisitor,
                                                 MethodCallbackFactory methodCallbackFactory,
                                                 int methodId,
                                                 String className) {
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodCallbackFactory.methodExitWithObjectReturn(methodId);
    }
}