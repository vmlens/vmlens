package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.FactoryContext;
import org.objectweb.asm.MethodVisitor;

public interface MethodVisitorAnalyzeAndTransformFactory {

    MethodVisitor createTransform(FactoryContext transformFactoryContext, MethodVisitor current);

    MethodVisitor createAnalyze(int methodId, MethodVisitor previous);

}
