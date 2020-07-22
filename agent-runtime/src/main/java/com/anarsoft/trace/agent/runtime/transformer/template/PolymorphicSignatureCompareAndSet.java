package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.Type;

public class PolymorphicSignatureCompareAndSet  extends TemplateMethodDescPolymorphicSignatureModify  {


	public PolymorphicSignatureCompareAndSet(String methodName) {
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
					 Type ret =  Type.getReturnType(desc);
					 if(  ret.getSort() == Type.BOOLEAN )
					 {
						 return true;
					 }
				 }
				 
		
				 
			 }
		 }
		 else  if( arguments.length == 4 )
		 {
			 if( arguments[0].getSort() == Type.ARRAY  && arguments[1].getSort() == Type.INT )
			 {
				 if(arguments[2].getSort()  == arguments[3].getSort())
				 {
					 Type ret =  Type.getReturnType(desc);
					 if(  ret.getSort() == Type.BOOLEAN )
					 {
						 return true;
					 }
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
	protected ApplyMethodTemplate createApplyMethodTemplate(String index ,  String argTyp , int sort ,  String argumentTypInternalName ,  Type returnType ) {
		return new ApplyMethodTemplateCall(CALL_BACK_CLASS  ,
				methodName + sort, "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;" + index +  argTyp + argTyp   + "I)Z");
	}

}
