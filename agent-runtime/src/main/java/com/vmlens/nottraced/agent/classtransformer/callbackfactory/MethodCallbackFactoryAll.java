package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CalleeFactory;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodCallbackFactoryAll extends DefaultMethodCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/transformed/agent/bootstrap/callback/MethodCallback";

    public MethodCallbackFactoryAll(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    public void methodEnter(int inMethodId, CalleeFactory calleeFactory) {
        calleeFactory.createCallee();
        methodCall(inMethodId, METHOD_ENTER, METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }

    public void methodCall(int id, String methodName, String methodDescriptor) {
        methodVisitor.visitLdcInsn(id);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, methodDescriptor, false);
    }


}
