package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import static org.objectweb.asm.Opcodes.DUP;

import org.objectweb.asm.MethodVisitor;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import com.vmlens.trace.agent.bootstrap.callback.MethodCallback;

public class Constructor implements MethodEnterExitStrategy {
   @Override
   public void createMethodEnter(MethodVisitor methodVisitor,
                                 MethodCallbackFactory methodCallbackFactory,
                                 int methodId,
                                 String className) {
      MethodCallback.constructorMethodEnter(methodId);
   }

   @Override
   public void createMethodExit(MethodVisitor methodVisitor,
                                 MethodCallbackFactory methodCallbackFactory,
                                 int methodId,
                                 String className) {
      MethodCallback.constructorMethodExit(methodId);
   }

   @Override
   public void createMethodExitWithObjectReturn(MethodVisitor methodVisitor,
                                                MethodCallbackFactory methodCallbackFactory,
                                                int methodId,
                                                String className) {
      methodVisitor.visitInsn(DUP);
      methodCallbackFactory.methodExitWithObjectReturn(methodId);
   }
}
