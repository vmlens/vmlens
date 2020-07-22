package com.anarsoft.trace.agent.runtime;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

public class FieldAnnotationVisitor extends AnnotationVisitor {

	public FieldAnnotationVisitor() {
		super(  AgentClassFileTransformer.ASM_API_VERSION );
		
	}

	@Override
	public void visit(String name, Object value) {
		
		
	}

	@Override
	public AnnotationVisitor visitAnnotation(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnnotationVisitor visitArray(String arg0) {
		
	
		
		return null;
	}

	@Override
	public void visitEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitEnum(String arg0, String arg1, String arg2) {
		
		
	}

}
