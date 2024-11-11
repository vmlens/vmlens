package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall;

import com.vmlens.trace.agent.bootstrap.repository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.repository.MethodCallIdMap;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static com.anarsoft.trace.agent.runtime.classtransformer.ASMConstants.ASM_API_VERSION;
import static org.objectweb.asm.Opcodes.*;

public class MethodVisitorAdapter extends MethodVisitor {

    private final MethodCallIdMap methodCallIdMap;
    private final AnalyzeOrTransformMethodMethodCall adapted;

    public MethodVisitorAdapter(MethodVisitor methodVisitor,
                                MethodCallIdMap methodCallIdMap,
                                AnalyzeOrTransformMethodMethodCall adapted) {
        super(ASM_API_VERSION, methodVisitor);
        this.methodCallIdMap = methodCallIdMap;
        this.adapted = adapted;
    }


    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        Type arguments = Type.getMethodType(descriptor);
        int argumentSize = 0;
        for (Type argType : arguments.getArgumentTypes()) {
            argumentSize += argType.getSize();
        }
        boolean isConstructorCall = "<init>".equals(name);
        int methodId = methodCallIdMap.asInt(new MethodCallId(owner, name, descriptor));
        adapted.beforeMethodCall(argumentSize, arguments.getReturnType().getSize(), isConstructorCall, methodId);
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        adapted.afterMethodCall(arguments.getReturnType().getSize(), methodId);
    }

    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);

        switch (opcode) {
            case DUP: {
                adapted.afterStackOperation(opcode);
                break;
            }

            default: {

            }
        }
    }

    @Override
    public void visitVarInsn(int opcode, int operand) {
        super.visitVarInsn(opcode, operand);

        switch (opcode) {
            case ALOAD: {
                adapted.afterLocalLoad(1);
                break;
            }

            default: {

            }
        }
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        super.visitTypeInsn(opcode, type);

        switch (opcode) {
            case NEW: {
                adapted.afterNew();
                break;
            }
            default: {

            }
        }
    }

}
