package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MonitorCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/transformed/agent/bootstrap/callback/MonitorCallback";
    private final String MONITOR_ENTER = "afterMonitorEnter";
    private final String BEFORE_MONITOR_EXIT = "beforeMonitorExit";
    private final String AFTER_MONITOR_EXIT = "afterMonitorExit";
    private final String METHOD_DESCRIPTOR_EMPTY = "()V";
    private final String METHOD_DESCRIPTOR_OBJECT_INT_INT_ARGUMENT = "(Ljava/lang/Object;II)V";

    private final MethodVisitor methodVisitor;
    private final int inMethodId;

    public MonitorCallbackFactory(MethodVisitor methodVisitor, int inMethodId) {
        this.methodVisitor = methodVisitor;
        this.inMethodId = inMethodId;
    }

    public void afterMonitorEnter(int position) {
        methodCall(position, MONITOR_ENTER);
    }

    public void beforeMonitorExit(int position) {
        methodCall(position, BEFORE_MONITOR_EXIT);
    }

    public void afterMonitorExit() {
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                AFTER_MONITOR_EXIT, METHOD_DESCRIPTOR_EMPTY, false);
    }

    private void methodCall(int position, String methodName) {
        methodVisitor.visitLdcInsn(inMethodId);
        methodVisitor.visitLdcInsn(position);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, METHOD_DESCRIPTOR_OBJECT_INT_INT_ARGUMENT, false);
    }
}
