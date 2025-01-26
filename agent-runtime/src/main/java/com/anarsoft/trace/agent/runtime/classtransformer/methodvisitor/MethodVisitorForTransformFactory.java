package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor;

import org.objectweb.asm.MethodVisitor;

public interface MethodVisitorForTransformFactory {

    MethodVisitor create(int methodId, MethodVisitor previous);

}
