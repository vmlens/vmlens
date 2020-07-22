package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.MethodVisitor;

public class ApplyMethodTemplateCallAndCast implements ApplyMethodTemplate {

	private final String callbackClass;
	private final String callbackMethod;
	private final String callbackDesc;
	private final String castTo;
	
	
	

		public ApplyMethodTemplateCallAndCast(String callbackClass, String callbackMethod, String callbackDesc, String castTo) {
		super();
		this.callbackClass = callbackClass;
		this.callbackMethod = callbackMethod;
		this.callbackDesc = callbackDesc;
		this.castTo = castTo;
	}




		public void apply(MethodVisitor mv)
		{
			 mv.visitMethodInsn(INVOKESTATIC, callbackClass , callbackMethod ,
					 callbackDesc , false);
			 
			
			 
			 
			 mv.visitTypeInsn(CHECKCAST , castTo);
			 
		}
	
	
}

