package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodCallbackFactoryPreAnalyzed extends MethodCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/trace/agent/bootstrap/callback/PreAnalyzedCallback";

    public MethodCallbackFactoryPreAnalyzed(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    protected void methodCall(int id, String methodName, String methodDescriptor) {
        methodVisitor.visitLdcInsn(id);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, methodDescriptor, false);
    }


}
