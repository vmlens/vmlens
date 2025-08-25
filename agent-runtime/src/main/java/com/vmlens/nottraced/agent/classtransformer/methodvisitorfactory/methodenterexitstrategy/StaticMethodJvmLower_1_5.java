package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class StaticMethodJvmLower_1_5 implements MethodEnterExitStrategy  {

    private final NeedsLoadsClassContainer needsLoadsClassContainer;

    public StaticMethodJvmLower_1_5(NeedsLoadsClassContainer needsLoadsClassContainer) {
        this.needsLoadsClassContainer = needsLoadsClassContainer;
    }

    @Override
    public void createMethodEnter(MethodVisitor methodVisitor,
                                  MethodCallbackFactory methodCallbackFactory,
                                  int methodId,
                                  String className) {
        needsLoadsClassContainer.setNeedsLoadMethod();
        methodVisitor.visitMethodInsn(INVOKESTATIC,
                className,
                "vmlensGeneratedLoadClass",
                "()Ljava/lang/Class;",
                false);
        methodCallbackFactory.methodEnter(methodId);
    }

    @Override
    public void createMethodExit(MethodVisitor methodVisitor,
                                 MethodCallbackFactory methodCallbackFactory,
                                 int methodId,
                                 String className) {
        needsLoadsClassContainer.setNeedsLoadMethod();
        methodVisitor.visitMethodInsn(INVOKESTATIC,
                className,
                "vmlensGeneratedLoadClass",
                "()Ljava/lang/Class;",
                false);
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
