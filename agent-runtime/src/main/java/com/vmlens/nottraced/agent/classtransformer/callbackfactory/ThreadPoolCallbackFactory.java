package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

import static com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory.METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class ThreadPoolCallbackFactory {

    private final String METHOD_DESCRIPTOR_OBJECT_OBJECT_INT_ARGUMENT_BOOLEAN_RETURN = "(Ljava/lang/Object;Ljava/lang/Object;I)Z";

    private final String CALLBACK_CLASS = "com/vmlens/trace/agent/bootstrap/callback/ThreadPoolCallback";
    private final String START = "start";
    private final String JOIN = "join";

    private final MethodVisitor methodVisitor;
    private final int methodId;

    public ThreadPoolCallbackFactory(MethodVisitor methodVisitor, int methodId) {
        this.methodVisitor = methodVisitor;
        this.methodId = methodId;
    }

    public void threadStart() {
        methodCall(START,METHOD_DESCRIPTOR_OBJECT_OBJECT_INT_ARGUMENT_BOOLEAN_RETURN);
    }

    public void threadJoin() {
        methodCall(JOIN,METHOD_DESCRIPTOR_OBJECT_INT_ARGUMENT);
    }

    private void methodCall(String methodName, String methodDescriptor) {
        methodVisitor.visitLdcInsn(methodId);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, methodDescriptor, false);
    }

}
