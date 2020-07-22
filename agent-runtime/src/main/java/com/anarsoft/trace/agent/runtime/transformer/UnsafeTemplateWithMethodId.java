package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.MethodVisitor;

public class UnsafeTemplateWithMethodId extends UnsafeTemplateAbstract {

	public UnsafeTemplateWithMethodId(String requeiredName,
			String requeiredDesc, String callBackDesc) {
		super(requeiredName, requeiredDesc, callBackDesc);
		
	}
	
	
	
	public void apply(MethodVisitor mv,int methodId)
	{
		mv.visitLdcInsn( methodId );
		 mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS , requeiredName ,
				 callBackDesc , false);
	}

}
