package com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

public abstract class MethodCallbackFactory {

    private final String BEFORE_METHOD_CALL = "beforeMethodCall";
    private final String AFTER_METHOD_CALL = "afterMethodCall";
    private final String METHOD_ENTER = "methodEnter";
    private final String METHOD_EXIT = "methodExit";
    private final String METHOD_DESCRIPTOR_INT_INT_INT_ARGUMENT = "(III)V";
    private final String METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT = "(Ljava/lang/Object;I)V";
    protected final MethodVisitor methodVisitor;

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

    public void methodEnter(int inMethodId) {
        methodCall(inMethodId, METHOD_ENTER, METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }

    public void methodExit(int inMethodId) {
        methodCall(inMethodId, METHOD_EXIT, METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }

    protected abstract void methodCall(int calledMethodId,
                                       String beforeMethodCall,
                                       String methodDescriptorIntIntIntArgument);

}
