package com.anarsoft.trace.agent.runtime.classtransformerall.methodvisitor;

import org.objectweb.asm.MethodVisitor;

public interface MethodVisitorAnalyzeAndTransformFactory {

    MethodVisitor createTransform(TransformFactoryContext transformFactoryContext);

    MethodVisitor createAnalyze(int methodId, MethodVisitor previous);

}
