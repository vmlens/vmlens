package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.Type;

public class PolymorphicSignatureSet extends TemplateMethodDescPolymorphicSignatureModify  {

	
	

	public PolymorphicSignatureSet(String methodName) {
		super(methodName);
	}

	@Override
	protected boolean descApplies(String desc) {
		
		 Type[] arguments = 	 Type.getArgumentTypes(desc);
			
		 if( arguments.length == 2 )
		 {
			 if( arguments[0].getSort() == Type.OBJECT)
			 {
				 Type ret =  Type.getReturnType(desc);
				 if(  ret.getSort() == Type.VOID )
				 {
					 return true;
				 }
				 
			 }
		 }
		 else  if( arguments.length == 3 )
		 {
			 if( arguments[0].getSort() == Type.ARRAY  && arguments[1].getSort()  == Type.INT)
			 {
				 Type ret =  Type.getReturnType(desc);
				 if(  ret.getSort() == Type.VOID )
				 {
					 return true;
				 }
				 
			 }
		 }
		 
		 
		 
		  return false;
		
		
		
	}
	

	protected  int getArgumentLength()
	{
		return 2;
	}
	

	@Override
	protected ApplyMethodTemplate createApplyMethodTemplate(String index ,  String argTyp , int sort ,  String argumentTypInternalName , Type returnType )   {
		
	
		
		return new ApplyMethodTemplateCall(CALL_BACK_CLASS  ,
				methodName +sort, "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;" + index + argTyp     + "I)V");
	}

}
