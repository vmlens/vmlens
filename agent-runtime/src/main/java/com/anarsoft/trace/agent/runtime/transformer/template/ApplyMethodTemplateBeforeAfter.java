package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.vmlens.shaded.gnu.trove.list.TLinkable;

public class ApplyMethodTemplateBeforeAfter implements  Opcodes ,TLinkable<ApplyMethodTemplateBeforeAfter> {

	 private final String requiredClass;
	 private final String requiredName;
	 private final String requiredDesc;
	 private final int requiredOpcode;
	
	 private final String callbackClass;
	 private final String callBackDesc;
	
	 private ApplyMethodTemplateBeforeAfter previous;
	 private ApplyMethodTemplateBeforeAfter next;

	 

	public ApplyMethodTemplateBeforeAfter getPrevious() {
		return previous;
	}


	public void setPrevious(ApplyMethodTemplateBeforeAfter previous) {
		this.previous = previous;
	}


	public ApplyMethodTemplateBeforeAfter getNext() {
		return next;
	}


	public void setNext(ApplyMethodTemplateBeforeAfter next) {
		this.next = next;
	}


	
	 
	 
	 
	public ApplyMethodTemplateBeforeAfter(String requiredClass, String requiredName, String requiredDesc,
			int requiredOpcode, String callbackClass, String callBackDesc) {
		super();
		this.requiredClass = requiredClass;
		this.requiredName = requiredName;
		this.requiredDesc = requiredDesc;
		this.requiredOpcode = requiredOpcode;
		this.callbackClass = callbackClass;
		this.callBackDesc = callBackDesc;
	}

	public void applyBefore(MethodVisitor mv)
	{
		mv.visitVarInsn(ALOAD, 0);
		 mv.visitMethodInsn(INVOKESTATIC, callbackClass , requiredName + "Before" ,
				 callBackDesc , false);
	}
	
	public void applyAfter(MethodVisitor mv)
	{
		 mv.visitMethodInsn(INVOKESTATIC, callbackClass , requiredName + "After" ,
				 "()V" , false);
	}


	public ApplyMethodTemplateBeforeAfter applies(int opcode, String owner, String name, String desc) {
	
		return this;
	}
	
}
