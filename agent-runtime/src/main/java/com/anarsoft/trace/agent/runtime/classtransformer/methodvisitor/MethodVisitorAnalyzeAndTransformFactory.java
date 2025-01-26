package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactoryContext;
import org.objectweb.asm.MethodVisitor;

public interface MethodVisitorAnalyzeAndTransformFactory {

    MethodVisitor createTransform(MethodVisitorFactoryContext transformFactoryContext, MethodVisitor current);

    MethodVisitor createAnalyze(int methodId, MethodVisitor previous);

}
