package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ALOAD;

public class NormalMethod extends AbstractMethodEnterExitStrategy {

    private static class NormalMethodCalleeFactory implements CalleeFactory{

        private final MethodVisitor methodVisitor;

        public NormalMethodCalleeFactory(MethodVisitor methodVisitor) {
            this.methodVisitor = methodVisitor;
        }

        @Override
        public void createCallee() {
            methodVisitor.visitVarInsn(ALOAD, 0);
        }
    }

    @Override
    protected CalleeFactory createCalleeFactory(MethodVisitor methodVisitor, String className) {
        return new NormalMethodCalleeFactory(methodVisitor);
    }


}