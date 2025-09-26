package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class FieldCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/transformed/agent/bootstrap/callback/FieldCallback";

    private final String BEFORE_FIELD_READ = "beforeFieldRead";
    private final String BEFORE_FIELD_WRITE = "beforeFieldWrite";
    private final String BEFORE_STATIC_FIELD_READ = "beforeStaticFieldRead";
    private final String BEFORE_STATIC_FIELD_WRITE = "beforeStaticFieldWrite";

    private final String AFTER_FIELD_ACCESS = "afterFieldAccess";

    private final String METHOD_DESCRIPTOR_OBJECT_INT_INT_INT_ARGUMENT = "(Ljava/lang/Object;III)V";
    private final String METHOD_DESCRIPTOR_INT_INT_INT_ARGUMENT = "(III)V";
    private final String METHOD_DESCRIPTOR_NO_ARGUMENT = "()V";

    private final MethodVisitor methodVisitor;
    private final int inMethodId;

    public FieldCallbackFactory(MethodVisitor methodVisitor, int inMethodId) {
        this.methodVisitor = methodVisitor;
        this.inMethodId = inMethodId;
    }

    public void beforeFieldRead(int fieldId, int position) {
        methodCall(fieldId, position, BEFORE_FIELD_READ);
    }

    public void beforeFieldWrite(int fieldId, int position) {
        methodCall(fieldId, position, BEFORE_FIELD_WRITE);
    }

    public void beforeStaticFieldRead(int fieldId, int position) {
        methodCallStatic(fieldId, position, BEFORE_STATIC_FIELD_READ);
    }

    public void beforeStaticFieldWrite(int fieldId, int position) {
        methodCallStatic(fieldId, position, BEFORE_STATIC_FIELD_WRITE);
    }

    public void afterFieldAccess() {
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                AFTER_FIELD_ACCESS, METHOD_DESCRIPTOR_NO_ARGUMENT, false);
    }

    private void methodCallStatic(int fieldId, int position, String methodName) {
        methodCall(fieldId, position, methodName, METHOD_DESCRIPTOR_INT_INT_INT_ARGUMENT);
    }

    private void methodCall(int fieldId, int position, String methodName) {
        methodCall(fieldId, position, methodName, METHOD_DESCRIPTOR_OBJECT_INT_INT_INT_ARGUMENT);
    }

    private void methodCall(int fieldId, int position, String methodName, String descriptor) {
        methodVisitor.visitLdcInsn(fieldId);
        methodVisitor.visitLdcInsn(position);
        methodVisitor.visitLdcInsn(inMethodId);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, descriptor, false);
    }
}
