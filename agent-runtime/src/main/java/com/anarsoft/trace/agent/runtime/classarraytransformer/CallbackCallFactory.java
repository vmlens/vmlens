package com.anarsoft.trace.agent.runtime.classarraytransformer;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class CallbackCallFactory {

    private final String METHOD_CALLBACK_CLASS = "com/vmlens/trace/agent/bootstrap/callback/MethodCallback";
    private final String BEFORE_METHOD_CALL = "beforeMethodCall";
    private final String AFTER_METHOD_CALL = "afterMethodCall";
    private final String METHOD_CALL_TARGET = "targetOfMethodCall";

    private final String METHOD_DESCRIPTOR_INT_ARGUMENT = "(I)V";
    private final String METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT = "(Ljava/lang/Object;I)V";

    private final MethodVisitor methodVisitor;

    public CallbackCallFactory(MethodVisitor methodVisitor) {
        this.methodVisitor = methodVisitor;
    }

    public void beforeMethodCall(int methodId) {
        methodCall(methodId, BEFORE_METHOD_CALL, METHOD_DESCRIPTOR_INT_ARGUMENT);
    }


    public void afterMethodCall(int methodId) {
        methodCall(methodId, AFTER_METHOD_CALL, METHOD_DESCRIPTOR_INT_ARGUMENT);
    }


    public void afterMethodCallTarget(int methodId) {
        methodVisitor.visitInsn(DUP);
        methodCall(methodId, METHOD_CALL_TARGET, METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }

    private void methodCall(int id, String methodName, String methodDescriptor) {
        methodVisitor.visitLdcInsn(id);
        methodVisitor.visitMethodInsn(INVOKESTATIC, METHOD_CALLBACK_CLASS,
                methodName, methodDescriptor, false);
    }


}
