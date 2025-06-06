package com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactory;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.Iterator;

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

        super.visitLabel(startLabel);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        super.visitTryCatchBlock(start, end, handler, type);
        currentTryCatchBlockCount++;
        if (currentTryCatchBlockCount == tryCatchBlockCount) {
            mv.visitTryCatchBlock(startLabel, endLabel, endLabel, "java/lang/Throwable");
        }
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitLabel(endLabel);

        Object[] localVariableArray = buildLocalVariables();

        if (useExpandedFrames) {
            mv.visitFrame(Opcodes.F_NEW, localVariableArray.length, localVariableArray, 1,
                    new Object[]{"java/lang/Throwable"});
        } else {
            mv.visitFrame(Opcodes.F_FULL, localVariableArray.length, localVariableArray, 1,
                    new Object[]{"java/lang/Throwable"});
        }

        createMethodExitCall();

        super.visitInsn(ATHROW);
        super.visitMaxs(maxStack, maxLocals);
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


    private Object[] buildLocalVariables() {
        TLinkedList<TLinkableWrapper<Object>> parameterList = new TLinkedList<TLinkableWrapper<Object>>();
        if (!isStatic) {
            parameterList.add(new TLinkableWrapper<>(className));

        }
        Type[] args = Type.getArgumentTypes(description);
        for (Type t : args) {
            parameterList.add(new TLinkableWrapper<Object>(getFrameType(t)));
        }

        Object[] result = new Object[parameterList.size()];
        Iterator<TLinkableWrapper<Object>> it = parameterList.iterator();
        int index = 0;

        while (it.hasNext()) {
            TLinkableWrapper<Object> c = it.next();
            result[index] = c.element();
            index++;

        }
        return result;
    }

    private Object getFrameType(Type type) {
        switch (type.getSort()) {
            case Type.BOOLEAN:
            case Type.CHAR:
            case Type.BYTE:
            case Type.SHORT:
            case Type.INT:
                return Opcodes.INTEGER;
            case Type.LONG:
                return Opcodes.LONG;
            case Type.FLOAT:
                return Opcodes.FLOAT;
            case Type.DOUBLE:
                return Opcodes.DOUBLE;
            case Type.OBJECT:
            case Type.ARRAY:
                return type.getInternalName();
        }
        throw new RuntimeException(type + " can not be converted to frame type");
    }
}
