package com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactory;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import static com.vmlens.nottraced.agent.classtransformer.ASMConstants.ASM_API_VERSION;
import static org.objectweb.asm.Opcodes.*;

/**
 * If an exception is thrown during the execution of a method, the Java Virtual Machine searches through
 * the exception table for a matching entry. An exception table entry matches if the current program
 * counter is within the range specified by the entry, and if the exception class thrown is the exception class
 * specified by the entry (or is a subclass of the specified exception class). The Java Virtual Machine
 * searches through the exception table in the order in which the entries appear in the table. When the first
 * match is found, the virtual machine sets the program counter to the new pc offset location and continues
 * execution there. If no match is found, the virtual machine pops the current stack frame and rethrows the
 * same exception.
 * see Inside the Java Virtual Machine by Bill Venners
 */


public class MethodEnterExitTransform extends MethodVisitor {

    private final Label startLabel = new Label();
    private final Label endLabel = new Label();

    private final int inMethodId;
    private final int tryCatchBlockCount;
    private final boolean useExpandedFrames;
    private final boolean isStatic;
    private final boolean isConstructor;
    private final String className;
    private final String description;
    private final MethodCallbackFactory methodCallbackFactory;

    private int currentTryCatchBlockCount = 0;

    public MethodEnterExitTransform(int inMethodId, int tryCatchBlockCount, boolean useExpandedFrames,
                                    MethodVisitor methodVisitor, boolean isStatic,
                                    boolean isConstructor, String className,
                                    String description, MethodCallbackFactoryFactory factoryFactory) {
        super(ASM_API_VERSION, methodVisitor);
        this.inMethodId = inMethodId;
        this.tryCatchBlockCount = tryCatchBlockCount;
        this.useExpandedFrames = useExpandedFrames;
        this.isStatic = isStatic;
        this.isConstructor = isConstructor;
        this.className = className;
        this.description = description;
        this.methodCallbackFactory = factoryFactory.create(this.mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        if (isStatic) {
                super.visitLdcInsn(Type.getType("L" + className + ";"));
        } else {
                super.visitVarInsn(ALOAD, 0);
        }
        methodCallbackFactory.methodEnter(inMethodId);

    }

    @Override
    public final void visitInsn(int inst) {
        switch (inst) {
            case RETURN:
            case IRETURN:
            case FRETURN:
            case LRETURN:
            case DRETURN:
            case ATHROW:
                createMethodExitCall();
                break;
            case ARETURN:
                if(methodCallbackFactory.supportsObjectReturn()) {
                    createMethodExitWithObjectReturnCall();
                } else {
                    createMethodExitCall();
                }
                break;
            default:
                break;

        }
        super.visitInsn(inst);
    }

    private void createMethodExitWithObjectReturnCall()  {
        if (isStatic) {
            super.visitLdcInsn(Type.getType("L" + className + ";"));
            methodCallbackFactory.methodExit(inMethodId);
        } else {
            // we currently only need the return value in
            // not static methods
            super.visitInsn(DUP);
            super.visitVarInsn(ALOAD, 0);
            methodCallbackFactory.methodExitWithObjectReturn(inMethodId);
        }

    }

    private void createMethodExitCall() {
        if (isStatic) {
            super.visitLdcInsn(Type.getType("L" + className + ";"));
        } else {
            super.visitVarInsn(ALOAD, 0);
        }
        methodCallbackFactory.methodExit(inMethodId);
    }

}
