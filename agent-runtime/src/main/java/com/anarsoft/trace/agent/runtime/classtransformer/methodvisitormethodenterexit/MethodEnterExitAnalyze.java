package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodenterexit;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static com.anarsoft.trace.agent.runtime.classtransformer.ASMConstants.ASM_API_VERSION;

public class MethodEnterExitAnalyze extends MethodVisitor {

    private final TryCatchBlockCounter tryCatchBlockCounter;

    public MethodEnterExitAnalyze(MethodVisitor methodVisitor, TryCatchBlockCounter tryCatchBlockCounter) {
        super(ASM_API_VERSION, methodVisitor);
        this.tryCatchBlockCounter = tryCatchBlockCounter;
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        super.visitTryCatchBlock(start, end, handler, type);
        tryCatchBlockCounter.incrementTryCatchBlockCount();
    }
}
