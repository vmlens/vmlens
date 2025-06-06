package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class ArrayCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/trace/agent/bootstrap/callback/ArrayCallback";

    private final static String BEFORE_ARRAY_READ = "beforeArrayRead";
    private final static String BEFORE_ARRAY_WRITE = "beforeArrayWrite";
    private final static String SET_LONG = "arraySetLong";
    private final static String SET_DOUBLE = "arraySetDouble";

    private final String OBJECT_INT_INT_ARGUMENT = "(Ljava/lang/Object;II)V";
    private final String LONG_ARRAY_INT_LONG_INT  = "([JIJI)V";
    private final String DOUBLE_ARRAY_INT_DOUBLE_INT = "([DIDI)V";


    private final MethodVisitor methodVisitor;
    private final int inMethodId;

    public ArrayCallbackFactory(MethodVisitor methodVisitor, int inMethodId) {
        this.methodVisitor = methodVisitor;
        this.inMethodId = inMethodId;
    }

    public void beforeArrayRead() {
        methodCall(BEFORE_ARRAY_READ);
    }

    public void beforeArrayWrite() {
        methodCall(BEFORE_ARRAY_WRITE);
    }

    public void arraySetLong() {
        methodCall(SET_LONG, LONG_ARRAY_INT_LONG_INT);
    }

    public void arraySetDouble() {
        methodCall(SET_DOUBLE, DOUBLE_ARRAY_INT_DOUBLE_INT);
    }


    private void methodCall(String methodName) {
        methodCall(methodName, OBJECT_INT_INT_ARGUMENT);
    }

    private void methodCall(String methodName, String description) {
        methodVisitor.visitLdcInsn(inMethodId);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, description, false);
    }


}
