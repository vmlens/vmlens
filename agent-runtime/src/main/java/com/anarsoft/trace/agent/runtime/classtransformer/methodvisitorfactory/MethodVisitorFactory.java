package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

import org.objectweb.asm.MethodVisitor;

public interface MethodVisitorFactory {

    MethodVisitor create(FactoryContext factoryContext, MethodVisitor previous);

}
