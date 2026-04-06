package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class StaticMethodJvmLower_1_5 implements CreateCalleeFactoryProvider  {

    private  class StaticMethodJvmLower_1_5CalleeFactory implements CalleeFactory{

        private final MethodVisitor methodVisitor;
        private final String className;

        public StaticMethodJvmLower_1_5CalleeFactory(MethodVisitor methodVisitor, String className) {
            this.methodVisitor = methodVisitor;
            this.className = className;
        }

        @Override
        public void createCallee() {
            needsLoadsClassContainer.setNeedsLoadMethod();
            methodVisitor.visitMethodInsn(INVOKESTATIC,
                    className,
                    "vmlensGeneratedLoadClass",
                    "()Ljava/lang/Class;",
                    false);
        }
    }

    private final NeedsLoadsClassContainer needsLoadsClassContainer;

    public StaticMethodJvmLower_1_5(NeedsLoadsClassContainer needsLoadsClassContainer) {
        this.needsLoadsClassContainer = needsLoadsClassContainer;
    }

    @Override
    public CalleeFactory createCalleeFactory(MethodVisitor methodVisitor, String className) {
        return new  StaticMethodJvmLower_1_5CalleeFactory(methodVisitor,className);
    }

}
