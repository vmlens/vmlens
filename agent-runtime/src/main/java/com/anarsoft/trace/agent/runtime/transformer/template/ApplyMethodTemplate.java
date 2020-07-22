package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public interface ApplyMethodTemplate extends  Opcodes {

	void apply(MethodVisitor mv);
	
}
