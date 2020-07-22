package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public abstract class UnsafeTemplateAbstract implements  Opcodes {
	
	 final String requeiredName;
	 final String requeiredDesc;
	 final String callBackDesc;
     final String CALLBACK_CLASS =  "com/vmlens/trace/agent/bootstrap/callback/UnsafeCallback";

	
	public UnsafeTemplateAbstract(String requeiredName,
			String requeiredDesc, String callBackDesc) {
		super();
		this.requeiredName = requeiredName;
		this.requeiredDesc = requeiredDesc;
		this.callBackDesc = callBackDesc;
	}
	
	
	public boolean applies(int opcode, String owner, String name,String desc)
	{
		return  opcode == INVOKEVIRTUAL 
				&& owner.equals("sun/misc/Unsafe") 
				&& name.equals( requeiredName )
				&& desc.equals(requeiredDesc ); 
			
	}
	
	
	public abstract  void apply(MethodVisitor mv,int methodId);
	
	

}
