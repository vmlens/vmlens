package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodCallbackFactoryAll extends MethodCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/trace/agent/bootstrap/callback/MethodCallback";

    public MethodCallbackFactoryAll(MethodVisitor methodVisitor) {
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
