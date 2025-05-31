package com.vmlens.nottraced.agent.classtransformer.logging;

import com.vmlens.nottraced.agent.classtransformer.ASMConstants;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.Printer;

import java.io.PrintWriter;

public class MethodVisitorForLogging extends MethodVisitor {

    private static final String SEPARATOR = "  ";
    private final PrintWriter printWriter;

    public MethodVisitorForLogging(PrintWriter printWriter) {
        super(ASMConstants.ASM_API_VERSION);
        this.printWriter = printWriter;
    }

    @Override
    public void visitInsn(int opcode) {
        print(opcode);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        print(opcode);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        print(opcode);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        print(opcode, SEPARATOR + type);
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        print(opcode);
    }

    @Override
    public void visitLdcInsn(Object value) {
        printWriter.println(SEPARATOR + "load:" + value);
    }


    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        print(opcode, owner, name, descriptor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        print(opcode, owner, name, descriptor);
    }

    private void print(int opcode, String owner, String name, String descriptor) {
        print(opcode, SEPARATOR + owner + "." + name + ":" + descriptor);
    }

    private void print(int opcode) {
        print(opcode, "");
    }

    private void print(int opcode, String text) {
        printWriter.println(SEPARATOR + Printer.OPCODES[opcode] + text);
    }

}
