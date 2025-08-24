package com.vmlens.nottraced.agent.classtransformervmlensapi;

import org.objectweb.asm.MethodVisitor;

import static com.vmlens.nottraced.agent.classtransformer.ASMConstants.ASM_API_VERSION;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class MethodVisitorReplaceMethodCall extends MethodVisitor {

    private static final String CALLBACK_CLASS = "com/vmlens/trace/agent/bootstrap/callback/VmlensApiCallback";

    public MethodVisitorReplaceMethodCall(MethodVisitor mv) {
        super(ASM_API_VERSION, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        /*
         * The difference between the invokespecial and the invokevirtual instructions is that
         * invokevirtual invokes a method based on the class of the object.
         * The invokespecial instruction is used to invoke instance initialization methods as well
         * as private methods and methods of a superclass of the current class.
         *
         * we need to filter out the init call in the constructor therefore the check for the name
         */

        if (opcode == INVOKESPECIAL && ( name.equals("hasNext") || name.equals("close")) ) {
            super.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS, name, descriptor, false);
        } else {
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        }
    }


}
