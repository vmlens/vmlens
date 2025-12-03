package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CalleeFactory;
import org.objectweb.asm.MethodVisitor;

public abstract class MethodCallbackFactory {

    public static final String METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT = "(Ljava/lang/Object;I)V";
    public static final String METHOD_ENTER = "methodEnter";

    private final String BEFORE_METHOD_CALL = "beforeMethodCall";
    private final String AFTER_METHOD_CALL = "afterMethodCall";
    public static  final String METHOD_EXIT = "methodExit";
    private final String METHOD_DESCRIPTOR_INT_INT_INT_ARGUMENT = "(III)V";

    protected final MethodVisitor methodVisitor;

    public MethodCallbackFactory(MethodVisitor methodVisitor) {
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

    public abstract void methodExit(int inMethodId, CalleeFactory calleeFactory);
    public abstract void methodExitWithObjectReturn(int inMethodId, CalleeFactory calleeFactory);
    public abstract void methodEnter(int inMethodId, CalleeFactory calleeFactory);

    public abstract void methodCall(int calledMethodId,
                                       String beforeMethodCall,
                                       String methodDescriptorIntIntIntArgument);

}
