package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodCallbackFactoryDoNotTrace extends MethodCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/trace/agent/bootstrap/callback/DoNotTraceCallback";

    public MethodCallbackFactoryDoNotTrace(MethodVisitor methodVisitor) {
        super(methodVisitor);
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
