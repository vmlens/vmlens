package com.anarsoft.trace.agent.runtime.transformer.template;

public class TemplateMethodDescSingleMethod extends TemplateMethodDesc {

	 private final String requiredClass;
	 private final String requiredName;
	 private final String requiredDesc;
	 private final int requiredOpcode;
	
	 private final String callbackClass;
	 private final String callBackDesc;
	
	
	 
	 
	
	
	@Override
	public String toString() {
		return "TemplateMethodDescSingleMethod [requiredClass=" + requiredClass + ", requiredName=" + requiredName
				+ ", requiredDesc=" + requiredDesc + ", requiredOpcode=" + requiredOpcode + "]";
	}






	public TemplateMethodDescSingleMethod(String requiredClass, String requiredName, String requiredDesc,
			int requiredOpcode, String callBackClass, String callBackDesc) {
		super();
		this.requiredClass = requiredClass;
		this.requiredName = requiredName;
		this.requiredDesc = requiredDesc;
		this.requiredOpcode = requiredOpcode;
		this.callbackClass = callBackClass;
		this.callBackDesc = callBackDesc;
	}






	@Override
	public ApplyMethodTemplate applies(int opcode, String owner, String name, String desc) {

		if(  opcode ==requiredOpcode &&  requiredClass.equals(owner) && requiredName.equals(name) &&
				requiredDesc.equals(desc)	)
		{
			return new ApplyMethodTemplateCall( callbackClass,  name,  callBackDesc);
		}
		
		
		return null;
	}

}
