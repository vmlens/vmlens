package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MonitorCallbackFactory {

    private final String CALLBACK_CLASS = "com/vmlens/transformed/agent/bootstrap/callback/MonitorCallback";
    private final String MONITOR_ENTER = "afterMonitorEnter";
    private final String MONITOR_EXIT = "afterMonitorExit";
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

    public void afterMonitorExit(int position) {
        methodCall(position, MONITOR_EXIT);
    }

    private void methodCall(int position, String methodName) {
        methodVisitor.visitLdcInsn(inMethodId);
        methodVisitor.visitLdcInsn(position);
        methodVisitor.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS,
                methodName, METHOD_DESCRIPTOR_OBJECT_INT_INT_ARGUMENT, false);
    }
}
