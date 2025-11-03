package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CalleeFactory;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class MethodCallbackFactoryPreAnalyzed extends MethodCallbackFactory {


    private final String CALLBACK_CLASS = "com/vmlens/transformed/agent/bootstrap/callback/PreAnalyzedCallback";
    private final String METHOD_EXIT_OBJECT_RETURN = "methodExitObjectReturn";
    private final String METHOD_DESCRIPTOR_OBJECT_OBJECT_INT_ARGUMENT = "(Ljava/lang/Object;Ljava/lang/Object;I)V";

    private final MethodEnterStrategy methodEnterStrategy;

    public MethodCallbackFactoryPreAnalyzed(MethodEnterStrategy methodEnterStrategy, MethodVisitor methodVisitor) {
        super(methodVisitor);
        this.methodEnterStrategy = methodEnterStrategy;
    }

    public void methodEnter(int inMethodId, CalleeFactory calleeFactory) {
        calleeFactory.createCallee();
        methodEnterStrategy.methodEnter(this,inMethodId);
    }

    @Override
    public void methodExitWithObjectReturn(int inMethodId, CalleeFactory calleeFactory) {
        methodVisitor.visitInsn(DUP);
        calleeFactory.createCallee();
        methodCall(inMethodId, METHOD_EXIT_OBJECT_RETURN, METHOD_DESCRIPTOR_OBJECT_OBJECT_INT_ARGUMENT);
    }

    @Override
    protected void methodCall(int id, String methodName, String methodDescriptor) {
        methodVisitor.visitLdcInsn(id);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, methodDescriptor, false);
    }

}
