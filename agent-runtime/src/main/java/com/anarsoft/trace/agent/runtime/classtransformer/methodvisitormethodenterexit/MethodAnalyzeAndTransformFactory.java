package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodenterexit;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.MethodVisitorAnalyzeAndTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactoryContext;
import org.objectweb.asm.MethodVisitor;

public class MethodAnalyzeAndTransformFactory implements MethodVisitorAnalyzeAndTransformFactory {

    private int tryCatchBlockCount = 0;

    public void incrementTryCatchBlockCount() {
        tryCatchBlockCount++;
    }

    @Override
    public MethodVisitor createTransform(MethodVisitorFactoryContext transformFactoryContext, MethodVisitor current) {
        return new TransformMethodMethod(transformFactoryContext.methodId(), tryCatchBlockCount, transformFactoryContext.useExpandedFrames(),
                current, transformFactoryContext.isStatic(), transformFactoryContext.isConstructor(), transformFactoryContext.className(),
                transformFactoryContext.description());
    }

    @Override
    public MethodVisitor createAnalyze(int methodId, MethodVisitor previous) {
        return new AnalyzeMethodMethod(previous, this);
    }
}

