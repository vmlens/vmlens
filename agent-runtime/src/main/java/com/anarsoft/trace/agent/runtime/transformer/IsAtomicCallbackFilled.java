package com.anarsoft.trace.agent.runtime.transformer;

public class IsAtomicCallbackFilled implements  IsAtomicCallback {

	private final String[] array;

	private final int id;

	public IsAtomicCallbackFilled(String[] className,int id) {
		super();
		this.array = className;

		this.id = id;
	}






	@Override
	public boolean isCallback(String className, String methodName, String desc) {
		
		for( String expected : array )
		{
			 if( expected.equals(className) )
			 {
				 return true;
			 }
		}
		
		
		return false;
	}

	

	@Override
	public int id() {
		return id;
	}


	@Override
	public boolean hasCallback() {
		return true;
	}
	
}
