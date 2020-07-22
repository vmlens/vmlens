package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.MethodVisitor;

public interface FactoryMethodTransformer {



	MethodVisitor create(MethodVisitor in);

}
