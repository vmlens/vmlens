package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.Type;

public class PolymorphicSignatureGet extends TemplateMethodDescPolymorphicSignature  {

	 


	

	public PolymorphicSignatureGet(String methodName) {
		super(methodName);
		
	}

	@Override
	protected boolean descApplies(String desc) {
		
	 Type[] arguments = 	 Type.getArgumentTypes(desc);
		
	 if( arguments.length == 1 )
	 {
		 if( arguments[0].getSort() == Type.OBJECT)
		 {
			 return true;
		 }
	 }
	 else  if( arguments.length == 2 )
	 {
		 if( arguments[0].getSort() == Type.ARRAY &&  arguments[1].getSort()  == Type.INT)
		 {
			 return true;
		 }
	 }
	 
	 
	 
	  return false;
	}
	
	
//	protected String getIndex(String desc)
//	{
//		 Type[] arguments = 	 Type.getArgumentTypes(desc);
//		 
//		 if( arguments.length == 1)
//		 {
//			 return "";
//		 }
//		 else
//		 {
//			 return "I";
//		 }
//			 
//		 
//	}
	
	protected  int getArgumentLength()
	{
		return 1;
	}
	

	@Override
	protected ApplyMethodTemplate createApplyMethodTemplate(String desc) {
		Type[] arguments = 	 Type.getArgumentTypes(desc);
		
		String index = getIndexString(arguments);
		Type returnType = 	Type.getReturnType(desc);
		  
		if(returnType.getSort() == Type.OBJECT)
		{
			
			
			return new ApplyMethodTemplateCallAndCast( CALL_BACK_CLASS ,
					methodName +  returnType.getSort(), "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;" +index +  "I)Ljava/lang/Object;" , returnType.getInternalName()  );
		}
		else
		{
			return new ApplyMethodTemplateCall(CALL_BACK_CLASS  ,
					methodName +  returnType.getSort(), "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;"  + index +  "I)" + returnType.getDescriptor() );
		}
		
		
	
		
		  
	
	}

//	@Override
//	protected String getCallbackDesc(String desc) {
//		
//	   Type returnType = 	Type.getReturnType(desc);
//		
//	   
//	   
//		
//		return "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;I)" + returnType.getDescriptor();
//	}
	
	
	 
	 
	 


	 
	
	
	
}
