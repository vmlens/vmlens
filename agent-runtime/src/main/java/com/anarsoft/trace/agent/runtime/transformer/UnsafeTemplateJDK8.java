package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.MethodVisitor;

public class UnsafeTemplateJDK8  extends UnsafeTemplateAbstract  {
	
	

	
	   final String CALLBACK_CLASS_JDK8 =  "com/vmlens/trace/agent/bootstrap/callback/UnsafeCallbackJDK8";
	/*
	 * UnsafeTemplateWithoutLocalMethod(String requeiredName,
			String requeiredDesc, String callBackDesc)
	 */
	
	public UnsafeTemplateJDK8(String name, String desc, String callBackDesc) {
		super(name , desc , callBackDesc);

		
	}



	
	public void apply(MethodVisitor mv,int methodId)
	{
		 mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS_JDK8 , requeiredName ,
				 callBackDesc);
	}
}
