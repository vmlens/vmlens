package com.anarsoft.trace.agent.runtime.classarraytransformer;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/trace/agent/bootstrap/callback/MethodCallback";
    private final String BEFORE_METHOD_CALL = "beforeMethodCall";
    private final String AFTER_METHOD_CALL = "afterMethodCall";
    private final String METHOD_CALL_TARGET = "targetOfMethodCall";

    private final String METHOD_DESCRIPTOR_INT_INT_INT_ARGUMENT = "(III)V";
    private final String METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT = "(Ljava/lang/Object;I)V";

    private final MethodVisitor methodVisitor;

    public MethodCallbackFactory(MethodVisitor methodVisitor) {
        this.methodVisitor = methodVisitor;
    }

    public void beforeMethodCall(int inMethodId, int position, int calledMethodId) {
        methodVisitor.visitLdcInsn(inMethodId);
        methodVisitor.visitLdcInsn(position);
        methodCall(calledMethodId, BEFORE_METHOD_CALL, METHOD_DESCRIPTOR_INT_INT_INT_ARGUMENT);
    }


    public void afterMethodCall(int inMethodId, int position, int calledMethodId) {
        methodVisitor.visitLdcInsn(inMethodId);
        methodVisitor.visitLdcInsn(position);
        methodCall(calledMethodId, AFTER_METHOD_CALL, METHOD_DESCRIPTOR_INT_INT_INT_ARGUMENT);
    }


    public void afterMethodCallTarget(int calledMethodId) {
        methodVisitor.visitInsn(DUP);
        methodCall(calledMethodId, METHOD_CALL_TARGET, METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }

    private void methodCall(int id, String methodName, String methodDescriptor) {
        methodVisitor.visitLdcInsn(id);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, methodDescriptor, false);
    }


}
