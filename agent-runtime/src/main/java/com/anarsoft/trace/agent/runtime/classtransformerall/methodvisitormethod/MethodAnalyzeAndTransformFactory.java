package com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitormethod;

import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor.TransformFactoryContext;
import org.objectweb.asm.MethodVisitor;

public class MethodAnalyzeAndTransformFactory implements MethodVisitorAnalyzeAndTransformFactory {

    private int tryCatchBlockCount = 0;

    public void incrementTryCatchBlockCount() {
        tryCatchBlockCount++;
    }

    @Override
    public MethodVisitor createTransform(TransformFactoryContext transformFactoryContext) {
        return new TransformMethodMethod(transformFactoryContext.methodId(), tryCatchBlockCount, transformFactoryContext.useExpandedFrames(),
                transformFactoryContext.previous(), transformFactoryContext.isStatic(), transformFactoryContext.isConstructor(), transformFactoryContext.className(),
                transformFactoryContext.description());
    }

    @Override
    public MethodVisitor createAnalyze(int methodId, MethodVisitor previous) {
        return new AnalyzeMethodMethod(previous, this);
    }
}

