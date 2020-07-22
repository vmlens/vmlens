package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.Type;

// mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/invoke/VarHandle", "getAndAdd", "(Lcom/vmlens/test/varHandle/TestVarHandle;I)I", false);

public class PolymorphicSignatureGetAndWithNumber  extends PolymorphicSignatureGetAndX  {

	
	public PolymorphicSignatureGetAndWithNumber(String methodName) {
		super(methodName);
	}
	
	
	protected boolean argumentTypOk(Type typ)
	{
		int sort =  typ.getSort();
		
		if( ( sort == Type.BYTE ) || (sort == Type.DOUBLE) || (sort == Type.FLOAT) || (sort == Type.INT) || (sort == Type.LONG) || (sort == Type.SHORT) || (sort == Type.CHAR) )
		{
			return true;
		}
		
		return false;
	}
	
	
	protected boolean returnTypOk(Type type)
	{
		if( type.getSort() == type.VOID )
		{
			return true;
		}
		
		return argumentTypOk(type);
	}
	
	protected  int getArgumentLength()
	{
		return 2;
	}
	
	
	
	@Override
	protected ApplyMethodTemplate createApplyMethodTemplate(String index ,  String argTyp , int sort ,  String argumentTypInternalName , Type returnType )  {
		
	
		
		return new ApplyMethodTemplateCall(CALL_BACK_CLASS  ,
				methodName + sort + "" + returnType.getSort() , "(Ljava/lang/invoke/VarHandle;Ljava/lang/Object;" + index  + argTyp  + "I)"  +returnType.getDescriptor() );
	}


}

