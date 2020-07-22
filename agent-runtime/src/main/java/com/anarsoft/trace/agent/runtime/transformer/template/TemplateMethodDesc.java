package com.anarsoft.trace.agent.runtime.transformer.template;

import com.vmlens.shaded.gnu.trove.list.TLinkable;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public abstract class TemplateMethodDesc implements  Opcodes , TLinkable<TemplateMethodDesc>{

	

	 
	 private TemplateMethodDesc previous;
	 private TemplateMethodDesc next;

	 

	public TemplateMethodDesc getPrevious() {
		return previous;
	}


	public void setPrevious(TemplateMethodDesc previous) {
		this.previous = previous;
	}


	public TemplateMethodDesc getNext() {
		return next;
	}


	public void setNext(TemplateMethodDesc next) {
		this.next = next;
	}


	
	
    public abstract ApplyMethodTemplate applies(int opcode, String owner, String name,String desc);
	


	
	
	
	
}
