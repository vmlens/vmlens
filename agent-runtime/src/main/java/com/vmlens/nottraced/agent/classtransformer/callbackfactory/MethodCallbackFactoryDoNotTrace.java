package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CalleeFactory;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodCallbackFactoryDoNotTrace extends DefaultMethodCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/transformed/agent/bootstrap/callback/DoNotTraceCallback";

    public MethodCallbackFactoryDoNotTrace(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    @Override
    public void methodEnter(int inMethodId, CalleeFactory calleeFactory) {
        calleeFactory.createCallee();
        methodCall(inMethodId, METHOD_ENTER, METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }

    @Override
    public void methodCall(int id, String methodName, String methodDescriptor) {
        methodVisitor.visitLdcInsn(id);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, methodDescriptor, false);
    }
}
