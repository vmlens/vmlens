package com.anarsoft.trace.agent.runtime.transformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;



/*
 * 
 * 	// mv.visitMethodInsn(opcode, owner, name, desc);
		 if(  opcode == INVOKEVIRTUAL && owner.equals("sun/misc/Unsafe") && name.equals( "compareAndSwapObject")
					&&   desc.equals("(Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z") ) 
			{
			 mv.visitMethodInsn(INVOKESTATIC, "com/vmlens/trace/agent/bootstrap/callback/UnsafeCallback", "compareAndSwapObject", "(Lsun/misc/Unsafe;Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z");
			}
 * 
 * @author Thomas
 *
 */


public class UnsafeTemplate extends UnsafeTemplateAbstract  {
	
	

	
	
	/*
	 * UnsafeTemplateWithoutLocalMethod(String requeiredName,
			String requeiredDesc, String callBackDesc)
	 */
	
	public UnsafeTemplate(String name, String desc, String callBackDesc) {
		super(name , desc , callBackDesc);

		
	}



	
	public void apply(MethodVisitor mv,int methodId)
	{
		 mv.visitMethodInsn(INVOKESTATIC, CALLBACK_CLASS , requeiredName ,
				 callBackDesc);
	}


}
