package com.anarsoft.trace.agent.runtime.classarraytransformer.methodvisitor;

import org.objectweb.asm.MethodVisitor;

public interface MethodVisitorFactory {

    MethodVisitor create(int methodId, MethodVisitor previous);

}
