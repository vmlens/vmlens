package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.Type;

public class PolymorphicSignatureCompareAndExchange extends TemplateMethodDescPolymorphicSignatureModify  {

	
	public PolymorphicSignatureCompareAndExchange(String methodName) {
		super(methodName);
	}
	
	
	@Override
	protected boolean descApplies(String desc) {
		
		 Type[] arguments = 	 Type.getArgumentTypes(desc);
			
		 if( arguments.length == 3 )
		 {
			 if( arguments[0].getSort() == Type.OBJECT)
			 {
				 if(arguments[1].getSort()  == arguments[2].getSort())
				 {
					
						 return true;
					
				 }
				 
		
				 
			 }
		 }
		 else  if( arguments.length == 4 )
		 {
			 if( arguments[0].getSort() == Type.ARRAY  && arguments[1].getSort() == Type.INT )
			 {
				 if(arguments[2].getSort()  == arguments[3].getSort())
				 {
					
						 return true;
				 }
				 
		
				 
			 }
		 }
		 
		 
		 
		  return false;
		
		
		
	}
	
	protected  int getArgumentLength()
	{
		return 3;
	}
	
	
	
	@Override
	protected ApplyMethodTemplate   createApplyMethodTemplate(String index ,  String argTyp , int sort ,   String argumentTypInternalName ,  Type returnType ){
		
	 
		  
		if(sort == Type.OBJECT)
		{
			
			if(returnType.getSort() == Type.OBJECT)
			{
				return new ApplyMethodTemplateCallAndCast(CALL_BACK_CLASS  ,
						  methodName + sort + returnType.getSort() , "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;"  + index +  argTyp + argTyp   + "I)Ljava/lang/Object;" 
						,argumentTypInternalName );
			}
			else
			{
				return new ApplyMethodTemplateCall(CALL_BACK_CLASS  ,
						methodName + sort + returnType.getSort() , "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;"  + index+  argTyp + argTyp   + "I)V" 
						 );
			}
			
	

		}
		else
		{
			return new ApplyMethodTemplateCall(CALL_BACK_CLASS  ,
					methodName + sort + returnType.getSort() , "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;" + index +  argTyp + argTyp   + "I)"  + returnType.getDescriptor());
			

		}
		
			}

}
