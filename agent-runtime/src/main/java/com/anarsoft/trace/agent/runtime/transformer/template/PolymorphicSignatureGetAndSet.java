package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.Type;

public class PolymorphicSignatureGetAndSet  extends PolymorphicSignatureGetAndX  {

	
	public PolymorphicSignatureGetAndSet(String methodName) {
		super(methodName);
	}
	
	
	protected boolean argumentTypOk(Type typ)
	{
		
			return true;
		
	}
	
	
	protected boolean returnTypOk(Type type)
	{
			return true;
		
	}
	
	protected  int getArgumentLength()
	{
		return 2;
	}
	
	
	@Override
	protected ApplyMethodTemplate createApplyMethodTemplate(String index ,  String argTyp , int sort ,  String argumentTypInternalName , Type returnType ) {
		
		
		if(sort == Type.OBJECT ||  sort == Type.VOID)
		{
			
			if(returnType.getSort() == Type.OBJECT)
			{
				String castTo = returnType.getInternalName();
				
//				if(sort == Type.VOID || castTo.equals("java/lang/Void"))
//				{
//					castTo = "java/lang/Object";
//				}
//			
			
				
				
				return new ApplyMethodTemplateCallAndCast(CALL_BACK_CLASS  ,
						methodName +  sort + "" + returnType.getSort() , "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;"+  index + "Ljava/lang/Object;I)Ljava/lang/Object;" 
						,   castTo );
			}
			else
			{
				return new ApplyMethodTemplateCall(CALL_BACK_CLASS  ,
						methodName +  sort + "" + returnType.getSort() , "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;"+  index+ "Ljava/lang/Object;I)V" 
						 );
			}
			
	

		}
		else
		{
			return new ApplyMethodTemplateCall(CALL_BACK_CLASS  ,
					methodName + sort + "" + returnType.getSort() , "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;" + index + argTyp  + "I)"  + returnType.getDescriptor() );

		}
		
			}

}