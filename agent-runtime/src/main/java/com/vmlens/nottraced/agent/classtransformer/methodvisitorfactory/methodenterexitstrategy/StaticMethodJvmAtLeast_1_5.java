package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class StaticMethodJvmAtLeast_1_5  extends AbstractMethodEnterExitStrategy  {

    private static class StaticMethodJvmAtLeast_1_5CalleeFactory implements CalleeFactory{

        private final MethodVisitor methodVisitor;
        private final String className;

        public StaticMethodJvmAtLeast_1_5CalleeFactory(MethodVisitor methodVisitor, String className) {
            this.methodVisitor = methodVisitor;
            this.className = className;
        }

        @Override
        public void createCallee() {
            methodVisitor.visitLdcInsn(Type.getType("L" + className + ";"));
        }
    }

    @Override
    protected CalleeFactory createCalleeFactory(MethodVisitor methodVisitor, String className) {
        return new StaticMethodJvmAtLeast_1_5CalleeFactory(methodVisitor,className);
    }

}
