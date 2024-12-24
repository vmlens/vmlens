package com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitormethod;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static com.anarsoft.trace.agent.runtime.classtransformerall.ASMConstants.ASM_API_VERSION;

public class AnalyzeMethodMethod extends MethodVisitor {

    private final MethodAnalyzeAndTransformFactory factoryForTransform;

    public AnalyzeMethodMethod(MethodVisitor methodVisitor, MethodAnalyzeAndTransformFactory factoryForTransform) {
        super(ASM_API_VERSION, methodVisitor);
        this.factoryForTransform = factoryForTransform;
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        super.visitTryCatchBlock(start, end, handler, type);
        factoryForTransform.incrementTryCatchBlockCount();
    }
}
