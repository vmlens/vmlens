package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodCallbackFactoryPreAnalyzed extends MethodCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/transformed/agent/bootstrap/callback/PreAnalyzedCallback";

    public MethodCallbackFactoryPreAnalyzed(MethodEnterStrategy methodEnterStrategy,MethodVisitor methodVisitor) {
        super(methodEnterStrategy,methodVisitor);
    }

    @Override
    public boolean supportsObjectReturn() {
        return true;
    }

    @Override
    protected void methodCall(int id, String methodName, String methodDescriptor) {
        methodVisitor.visitLdcInsn(id);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, methodDescriptor, false);
    }

}
