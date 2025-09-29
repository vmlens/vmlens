package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodCallbackFactoryDoNotTrace extends MethodCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/transformed/agent/bootstrap/callback/DoNotTraceCallback";

    public MethodCallbackFactoryDoNotTrace(MethodVisitor methodVisitor) {
        super(new MethodEnterStrategyWithoutParam(),methodVisitor);
    }

    @Override
    public boolean supportsObjectReturn() {
        return false;
    }

    protected void methodCall(int id, String methodName, String methodDescriptor) {
        methodVisitor.visitLdcInsn(id);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, methodDescriptor, false);
    }
}
