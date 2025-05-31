package com.vmlens.nottraced.agent.classtransformer.methodvisitor;

import com.vmlens.nottraced.agent.classtransformer.ASMConstants;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.ArrayCallbackFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class AddArrayAccessAccessCall extends MethodVisitor {

    private final ArrayCallbackFactory arrayCallbackFactory;

    public AddArrayAccessAccessCall(MethodVisitor methodVisitor, int inMethodId) {
        super(ASMConstants.ASM_API_VERSION, methodVisitor);
        arrayCallbackFactory = new ArrayCallbackFactory(methodVisitor,inMethodId);
    }

    public static MethodVisitorFactory factory() {
        return (factoryContext, previous) -> new AddArrayAccessAccessCall(previous, factoryContext.methodId());
    }

    @Override
    public void visitInsn(int opcode) {
        switch (opcode) {
            case IALOAD:
            case FALOAD:
            case AALOAD:
            case BALOAD:
            case CALOAD:
            case SALOAD:
            case LALOAD:
            case DALOAD:
                onArrayLoad();
                super.visitInsn(opcode);
                break;

            case IASTORE:
            case FASTORE:
            case AASTORE:
            case BASTORE:
            case CASTORE:
            case SASTORE:
                onArrayStore();
                super.visitInsn(opcode);
                break;

            case LASTORE:
                arrayCallbackFactory.arraySetLong();;
                break;
            case DASTORE:
                arrayCallbackFactory.arraySetDouble();
                break;
            default:
                super.visitInsn(opcode);
        }

    }

    private void onArrayStore() {
     /*
        ALOAD 2
        BIPUSH 8
        LDC "test"
        AASTORE
      */
        this.mv.visitInsn(DUP_X2);
        // ... , v1 , v2 , v3  ->   v3 , v1 , v2 , v3
        this.mv.visitInsn(POP);
        // v3 , v1 , v2 , v3 -> v3 , v1 , v2
        this.mv.visitInsn(DUP2_X1);
        // v3 , v1 , v2 -> v1 , v2, v3, v1 , v2
        arrayCallbackFactory.beforeArrayWrite();
    }

    private void onArrayLoad() {
     /*
        ALOAD 2
        ICONST_3
        AALOAD
      */
        this.mv.visitInsn(DUP2);
        arrayCallbackFactory.beforeArrayRead();
    }
}






