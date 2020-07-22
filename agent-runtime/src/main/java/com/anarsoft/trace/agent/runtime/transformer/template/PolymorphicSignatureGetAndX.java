package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.Type;

public abstract class PolymorphicSignatureGetAndX extends TemplateMethodDescPolymorphicSignatureModify  {

	
	public PolymorphicSignatureGetAndX(String methodName) {
		super(methodName);
	}
	
	
	protected abstract boolean argumentTypOk(Type typ);
	protected abstract boolean returnTypOk(Type typ);
	
	
	

	@Override
	protected boolean descApplies(String desc) {
		
		 Type[] arguments = 	 Type.getArgumentTypes(desc);
			
		 if( arguments.length == 2 )
		 {
			 if( arguments[0].getSort() == Type.OBJECT)
			 {
					Type argumentType = arguments[1];
					
					if(  argumentTypOk(argumentType) && returnTypOk(Type.getReturnType(desc)) )
					{
						return true;
					}
				 
				 
				 
				 
			 }
		 }
		 else  if( arguments.length == 3 )
		 {
			 if( arguments[0].getSort() == Type.ARRAY && arguments[1].getSort() == Type.INT )
			 {
					Type argumentType = arguments[2];
					
					if(  argumentTypOk(argumentType) && returnTypOk(Type.getReturnType(desc)) )
					{
						return true;
					}
				 
				 
				 
				 
			 }
		 }
		 
		 
		 
		  return false;
		
		
		
	}



}
