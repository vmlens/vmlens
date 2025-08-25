package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import org.objectweb.asm.MethodVisitor;

/**
 *
 * This is for the case bytecode lower 1.5 and during retransform, e.g. no methods can be added
 */
public class StaticMethodNoOp implements MethodEnterExitStrategy {
    @Override
    public void createMethodEnter(MethodVisitor methodVisitor,
                                  MethodCallbackFactory methodCallbackFactory,
                                  int methodId,
                                  String className) {

    }

    @Override
    public void createMethodExit(MethodVisitor methodVisitor,
                                 MethodCallbackFactory methodCallbackFactory,
                                 int methodId,
                                 String className) {

    }

    @Override
    public void createMethodExitWithObjectReturn(MethodVisitor methodVisitor,
                                                 MethodCallbackFactory methodCallbackFactory,
                                                 int methodId,
                                                 String className) {

    }
}
