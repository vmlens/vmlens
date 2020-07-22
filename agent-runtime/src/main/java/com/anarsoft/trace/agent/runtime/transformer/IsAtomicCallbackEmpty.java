package com.anarsoft.trace.agent.runtime.transformer;

public class IsAtomicCallbackEmpty  implements  IsAtomicCallback  {

	private final int id;
	
	
	
	
	
	public IsAtomicCallbackEmpty(int id) {
		super();
		this.id = id;
	}





	@Override
	public boolean isCallback(String className, String methodName, String desc) {
		return false;
	}





	@Override
	public int id() {
		return id;
	}





	@Override
	public boolean hasCallback() {
		return false;
	}

}
