package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

public abstract class MethodCallbackFactory {

    public static final String METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT = "(Ljava/lang/Object;I)V";

    private final String BEFORE_METHOD_CALL = "beforeMethodCall";
    private final String AFTER_METHOD_CALL = "afterMethodCall";
    private final String METHOD_EXIT = "methodExit";
    private final String METHOD_EXIT_OBJECT_RETURN = "methodExitObjectReturn";
    private final String METHOD_DESCRIPTOR_INT_INT_INT_ARGUMENT = "(III)V";
    private final String METHOD_DESCRIPTOR_OBJECT_OBJECT_INT_ARGUMENT = "(Ljava/lang/Object;Ljava/lang/Object;I)V";
    private final MethodEnterStrategy methodEnterStrategy;
    protected final MethodVisitor methodVisitor;

    public MethodCallbackFactory(MethodEnterStrategy methodEnterStrategy,
                                 MethodVisitor methodVisitor) {
        this.methodEnterStrategy = methodEnterStrategy;
        this.methodVisitor = methodVisitor;
    }

    public MethodVisitor methodVisitor() {
        return methodVisitor;
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
        methodEnterStrategy.methodEnter(this,inMethodId);
    }

    public void methodExit(int inMethodId) {
        methodCall(inMethodId, METHOD_EXIT, METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }

    public void methodExitWithObjectReturn(int inMethodId) {
        methodCall(inMethodId, METHOD_EXIT_OBJECT_RETURN, METHOD_DESCRIPTOR_OBJECT_OBJECT_INT_ARGUMENT);
    }

    public abstract boolean supportsObjectReturn();

    protected abstract void methodCall(int calledMethodId,
                                       String beforeMethodCall,
                                       String methodDescriptorIntIntIntArgument);

}
