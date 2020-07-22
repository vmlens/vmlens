package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.anarsoft.trace.agent.runtime.AgentClassFileTransformer;
import com.anarsoft.trace.agent.runtime.MethodDescriptionBuilder;

public class MethodTransformerTraceLineNumber  extends MethodVisitor {


	protected final MethodDescriptionBuilder methodDescriptionBuilder;

	
	public MethodTransformerTraceLineNumber(MethodVisitor mv, MethodDescriptionBuilder methodDescriptionBuilder) {
		super( AgentClassFileTransformer.ASM_API_VERSION  , mv);
		this.methodDescriptionBuilder = methodDescriptionBuilder;
	}


	@Override
	public void visitLineNumber(int line, Label start) {
		super.visitLineNumber(line, start);
		
		methodDescriptionBuilder.setLineNumber(line);
		
	}


	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		
		
		methodDescriptionBuilder.addAnnotation ( desc  );
		
		return super.visitAnnotation(desc, visible);
	}	

	
	
	
	
	

}
