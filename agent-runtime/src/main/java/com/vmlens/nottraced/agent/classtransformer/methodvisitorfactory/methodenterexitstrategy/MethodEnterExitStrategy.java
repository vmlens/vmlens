package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import org.objectweb.asm.MethodVisitor;

/*
 * Double dispatch to resolve the two dimensional decision:
 *   if a call should be created and how the callee should be created
 *   depends on the class version and if the method is static or not
 *
 *   how many parameter are used is defined by the callback strategy
 *
 */

public interface MethodEnterExitStrategy {

    void createMethodEnter(MethodVisitor methodVisitor,
                           MethodCallbackFactory methodCallbackFactory,
                           int methodId,
                           String className);

    void createMethodExit(MethodVisitor methodVisitor,
                          MethodCallbackFactory methodCallbackFactory,
                          int methodId,
                          String className);

    void createMethodExitWithObjectReturn(MethodVisitor methodVisitor,
                          MethodCallbackFactory methodCallbackFactory,
                          int methodId,
                          String className);

}
