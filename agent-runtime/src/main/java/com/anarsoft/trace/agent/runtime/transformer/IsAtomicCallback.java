package com.anarsoft.trace.agent.runtime.transformer;

public interface IsAtomicCallback {
	
	
	boolean isCallback(  String className, String methodName , String desc );
	int id();
	
	boolean hasCallback();
	

}
