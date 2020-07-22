package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.MethodVisitor;

public class ApplyMethodTemplateCall implements ApplyMethodTemplate {

	private final String callbackClass;
	private final String callbackMethod;
	private final String callbackDesc;
	
	
	

		@Override
	public String toString() {
		return "ApplyMethodTemplateCall [callbackClass=" + callbackClass + ", callbackMethod=" + callbackMethod
				+ ", callbackDesc=" + callbackDesc + "]";
	}




		public ApplyMethodTemplateCall(String callbackClass, String callbackMethod, String callbackDesc) {
		super();
		this.callbackClass = callbackClass;
		this.callbackMethod = callbackMethod;
		this.callbackDesc = callbackDesc;
	}




		public void apply(MethodVisitor mv)
		{
			 mv.visitMethodInsn(INVOKESTATIC, callbackClass , callbackMethod ,
					 callbackDesc , false);
		}
	
	
}
