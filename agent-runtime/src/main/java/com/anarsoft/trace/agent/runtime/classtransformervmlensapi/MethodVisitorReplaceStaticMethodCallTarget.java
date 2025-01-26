package com.anarsoft.trace.agent.runtime.classtransformervmlensapi;

import org.objectweb.asm.MethodVisitor;

import static com.anarsoft.trace.agent.runtime.classtransformer.ASMConstants.ASM_API_VERSION;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodVisitorReplaceStaticMethodCallTarget extends MethodVisitor {

    private static final String CALLBACK_CLASS = "com/vmlens/trace/agent/bootstrap/callback/VmlensApiCallback";

    public MethodVisitorReplaceStaticMethodCallTarget(MethodVisitor mv) {
        super(ASM_API_VERSION, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {

        if (opcode == INVOKESTATIC) {
            super.visitMethodInsn(opcode, CALLBACK_CLASS, name, descriptor, isInterface);
        } else {
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }
    }


}
