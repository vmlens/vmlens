package com.anarsoft.trace.agent.runtime.classtransformervmlensapi;

import org.objectweb.asm.MethodVisitor;

import static com.anarsoft.trace.agent.runtime.classtransformer.ASMConstants.ASM_API_VERSION;
import static com.anarsoft.trace.agent.runtime.classtransformervmlensapi.ClassVisitorVmlensApi.CALLBACK_CLASS_VMLENS_API;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodVisitorClose extends MethodVisitor {

    private final String name;
    private final String desc;

    public MethodVisitorClose(MethodVisitor mv, String name, String desc) {
        super(ASM_API_VERSION, mv);
        this.name = name;
        this.desc = desc;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_VMLENS_API, name, desc);
    }

}
