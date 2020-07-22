package com.anarsoft.trace.agent.runtime.transformer.template;

import org.objectweb.asm.Type;

public abstract class TemplateMethodDescPolymorphicSignatureModify extends TemplateMethodDescPolymorphicSignature {

	public TemplateMethodDescPolymorphicSignatureModify(String methodName) {
		super(methodName);
	}

	protected abstract ApplyMethodTemplate createApplyMethodTemplate(String index ,  String argTyp , int sort ,  String argumentTypInternalName , Type returnType );
	
	
	@Override
	protected final ApplyMethodTemplate createApplyMethodTemplate(String desc) {
		Type[] arguments = 	 Type.getArgumentTypes(desc);
		Type argumentType = getArgumentType(arguments);
		String index = getIndexString(arguments);
		
		String argTyp = argumentType.getDescriptor();
		
		int sort = argumentType.getSort();
		
		
		
		
		if(argumentType.getSort() == Type.OBJECT)
		{
			argTyp = "Ljava/lang/Object;";
			
			sort = Type.OBJECT;
		}
		
		Type returnType = 	Type.getReturnType(desc);
	
		return createApplyMethodTemplate( index ,  argTyp , sort , argumentType.getInternalName() , returnType);
	}

	
	
	protected Type getArgumentType(Type[] arguments)
	{
		if( arguments.length ==   getArgumentLength())
		{
			return arguments[1];
		}
		else
		{
			return arguments[2];
		}
	}
	
	
	
}
