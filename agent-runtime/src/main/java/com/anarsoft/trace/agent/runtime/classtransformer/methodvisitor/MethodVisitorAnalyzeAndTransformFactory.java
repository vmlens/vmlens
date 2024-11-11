package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor;

import org.objectweb.asm.MethodVisitor;

public interface MethodVisitorAnalyzeAndTransformFactory {

    MethodVisitor createTransform(TransformFactoryContext transformFactoryContext);

    MethodVisitor createAnalyze(int methodId, MethodVisitor previous);

}
