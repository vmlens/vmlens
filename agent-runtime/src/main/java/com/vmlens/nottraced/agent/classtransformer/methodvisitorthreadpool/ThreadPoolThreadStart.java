package com.vmlens.nottraced.agent.classtransformer.methodvisitorthreadpool;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.ThreadPoolCallbackFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static com.vmlens.nottraced.agent.classtransformer.ASMConstants.ASM_API_VERSION;
import static org.objectweb.asm.Opcodes.*;

/*
   L0
    LINENUMBER 6 L0
    ALOAD 0
    ALOAD 1
    INVOKESTATIC com/vmlens/test/guineapig/ThreadPoolExecutorGuineaPig.call (Ljava/lang/Object;Ljava/lang/Object;)Z
    IFEQ L1
   L2
    LINENUMBER 7 L2
    RETURN
   L1

    ifeq Label      ; if the value of local variable 1 is zero, jump to Label
 */


public class ThreadPoolThreadStart extends MethodVisitor  {

    public static MethodVisitorFactory factory() {
        return (factoryContext, previous) -> new ThreadPoolThreadStart(previous,
                factoryContext.methodId());
    }

    private final ThreadPoolCallbackFactory threadPoolCallbackFactory;

    public ThreadPoolThreadStart(MethodVisitor methodVisitor, int methodId) {
        super(ASM_API_VERSION, methodVisitor);
        this.threadPoolCallbackFactory = new ThreadPoolCallbackFactory(methodVisitor,methodId);
    }

    @Override
    public void visitCode() {
        Label jumpToLabel = new Label();

        super.visitCode();
        super.visitVarInsn(ALOAD, 0);
        super.visitVarInsn(ALOAD, 1);
        threadPoolCallbackFactory.threadStart();
        super.visitJumpInsn(IFEQ, jumpToLabel);
        super.visitInsn(RETURN);

        super.visitLabel(jumpToLabel);
    }

}
