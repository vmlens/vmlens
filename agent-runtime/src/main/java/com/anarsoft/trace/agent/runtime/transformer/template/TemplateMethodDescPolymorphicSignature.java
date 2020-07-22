package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.Type;

public abstract class TemplateMethodDescPolymorphicSignature extends TemplateMethodDesc  {

	protected final String CALL_BACK_CLASS = "com/vmlens/trace/agent/bootstrap/callback/gen/VarHandleCallbackGen";
	protected final String methodName;

	
	public TemplateMethodDescPolymorphicSignature(String methodName) {
		super();
		this.methodName = methodName;
	}
	
	 
	protected abstract boolean descApplies( String desc  );
	protected abstract ApplyMethodTemplate createApplyMethodTemplate(String desc);
	

	protected abstract int getArgumentLength();
	
	
	
	protected String getIndexString(Type[] arguments)
	{
		if( arguments.length ==   getArgumentLength())
		{
			return "";
		}
		else
		{
			return "I";
		}
	}
	
	
	
	
//	
	
	
	public ApplyMethodTemplate applies(int opcode, String owner, String name, String desc) {
		
		if(  opcode == INVOKEVIRTUAL && owner.equals("java/lang/invoke/VarHandle")  && name.equals(methodName) )
		{
			// ApplyMethodTemplate(String callbackClass, String callbackMethod, String callbackDesc) 
			if( descApplies( desc ) )
			{
				return    createApplyMethodTemplate(desc); //new ApplyMethodTemplate("com/vmlens/trace/agent/bootstrap/callback/VarHandleCallback"  ,name , getCallbackDesc(desc) );
			}
			else
			{
				System.err.println("unknown desc for template " + desc + " " + name );
				return null;
			}
			
			
		}
		else
		{
			return null;
		}
		
		
		
	}
	
	
}
